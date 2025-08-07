package com.example.enuyguncase.presentation.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.enuyguncase.R
import com.example.enuyguncase.databinding.FragmentHomeBinding
import com.example.enuyguncase.presentation.common.navigation.NavigationRouter
import com.example.enuyguncase.presentation.home.filter.FilterSheetFragment
import com.example.enuyguncase.presentation.home.sort.SortSheetFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: HomeViewModel by activityViewModels()
    private lateinit var adapter: ProductListAdapter
    
    @Inject
    lateinit var navigationRouter: NavigationRouter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) = FragmentHomeBinding.inflate(inflater, container, false).also {
        _binding = it
        it.vm = viewModel
        it.lifecycleOwner = viewLifecycleOwner
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setToolbar()
        setAdapters()
        setObservers()
        setListeners()
        setupWindowInsets()
        setupClickOutsideToHideKeyboard()
    }

    private fun setupClickOutsideToHideKeyboard() {
        binding.root.setOnClickListener {
            hideKeyboard()
        }
        binding.rvProducts.setOnClickListener {
            hideKeyboard()
        }
    }

    private fun hideKeyboard() {
        val currentFocus = requireActivity().currentFocus
        if (currentFocus != null) {
            val imm = ContextCompat.getSystemService(requireContext(), InputMethodManager::class.java)
            imm?.hideSoftInputFromWindow(currentFocus.windowToken, 0)
            currentFocus.clearFocus()
        }
    }

    private fun setupWindowInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { _, windowInsets ->
            val insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars())
            
            binding.root.setPadding(
                binding.root.paddingLeft,
                insets.top,
                binding.root.paddingRight,
                binding.root.paddingBottom
            )
            
            WindowInsetsCompat.CONSUMED
        }
    }

    private fun setToolbar() {
        (activity as? AppCompatActivity)?.supportActionBar?.hide()
    }

    private fun setAdapters() {
        adapter = ProductListAdapter { product ->
            navigationRouter.navigateToProductDetail(findNavController(), product.id)
        }
        binding.rvProducts.adapter = adapter
        binding.rvProducts.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun setListeners() {
        binding.filterSortBar.btnFilter.setOnClickListener{
            hideKeyboard() // Filter açılırken keyboard'u kapat
            FilterSheetFragment().show(childFragmentManager, "filter_sheet")
        }

        binding.filterSortBar.btnSort.setOnClickListener{
            hideKeyboard() // Sort açılırken keyboard'u kapat
            SortSheetFragment().show(childFragmentManager, "sort_sheet")
        }

        binding.searchBar.etSearch.doAfterTextChanged {
            viewModel.searchProducts(it.toString())
        }

        binding.btnRetry.setOnClickListener {
            viewModel.fetchProducts()
        }

        binding.rvProducts.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition()
                val totalItemCount = layoutManager.itemCount

                val isLoading = viewModel.uiState.value.isLoading
                val totalItems = viewModel.uiState.value.total

                if (lastVisibleItemPosition >= totalItemCount - 5 && !isLoading && totalItemCount < totalItems) {
                    viewModel.loadNextPage()
                }
            }
        })
    }

    private fun setObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { state ->
                    if (state.isLoading && state.products.isEmpty()) {
                        adapter.setLoading(true)
                        binding.tvCount.text = getString(R.string.home_total_products_format, 0)
                        binding.errorLayout.visibility = View.GONE
                        binding.rvProducts.visibility = View.VISIBLE
                    } else if (state.error != null && state.products.isEmpty()) {
                        adapter.setLoading(false)
                        binding.errorLayout.visibility = View.VISIBLE
                        binding.rvProducts.visibility = View.GONE
                        binding.tvError.text = state.error
                        binding.tvCount.text = getString(R.string.home_total_products_format, 0)
                    } else {
                        adapter.setLoading(false)
                        adapter.submitProducts(state.products)
                        binding.tvCount.text = getString(R.string.home_total_products_format, state.total)
                        binding.errorLayout.visibility = View.GONE
                        binding.rvProducts.visibility = View.VISIBLE
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}