package com.example.enuyguncase.presentation.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.enuyguncase.databinding.FragmentHomeBinding
import com.example.enuyguncase.presentation.home.filter.FilterSheetFragment
import com.example.enuyguncase.presentation.home.sort.SortSheetFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: HomeViewModel by activityViewModels()
    private lateinit var adapter: ProductListAdapter


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
    }

    private fun setToolbar() {
        (activity as? AppCompatActivity)?.supportActionBar?.apply {
            title = ""
            setDisplayHomeAsUpEnabled(false)
            setDisplayShowHomeEnabled(false)
        }

    }

    private fun setAdapters() {
        adapter = ProductListAdapter {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToProductDetailFragment(it.id))
        }
        binding.rvProducts.adapter = adapter
        binding.rvProducts.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun setListeners() {
        binding.filterSortBar.btnFilter.setOnClickListener{
            FilterSheetFragment().show(childFragmentManager, "filter_sheet")
        }

        binding.filterSortBar.btnSort.setOnClickListener{
            SortSheetFragment().show(childFragmentManager, "sort_sheet")
        }

        binding.searchBar.etSearch.doAfterTextChanged {
            viewModel.searchProducts(it.toString())
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
                    adapter.submitList(state.products)
                    /* binding.progressBar.isVisible = state.error != null
                     binding.tvError.isVisible = state.error != null
                     binding.tvError.text = state.error*/
                }
            }

        }

    }

}