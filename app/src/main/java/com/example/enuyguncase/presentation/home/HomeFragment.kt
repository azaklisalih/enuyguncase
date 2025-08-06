package com.example.enuyguncase.presentation.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.enuyguncase.R
import com.example.enuyguncase.databinding.FragmentHomeBinding
import com.google.android.material.appbar.MaterialToolbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: HomeViewModel by viewModels()
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

    fun setToolbar() {
        (activity as? AppCompatActivity)?.supportActionBar?.apply {
            title = ""
            setDisplayHomeAsUpEnabled(false)
            setDisplayShowHomeEnabled(false)
        }

    }

    fun setAdapters() {
        adapter = ProductListAdapter {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToProductDetailFragment(it.id))
        }
        binding.rvProducts.adapter = adapter
        binding.rvProducts.layoutManager = LinearLayoutManager(requireContext())
    }

    fun setListeners() {
        binding.filterSortBar.btnFilter.setOnClickListener{
            // Filter işlemleri burada yapılır
        }

        binding.filterSortBar.btnFilter.setOnClickListener{
            // Sort işlemleri burada yapılır
        }

        binding.searchBar.etSearch.doAfterTextChanged {
            viewModel.searchProducts(it.toString())
        }
    }

    fun setObservers() {
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