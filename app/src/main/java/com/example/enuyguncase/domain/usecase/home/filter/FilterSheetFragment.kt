package com.example.enuyguncase.domain.usecase.home.filter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.setPadding
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.example.enuyguncase.databinding.FilterSheetBinding
import com.example.enuyguncase.presentation.home.HomeViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.chip.Chip
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class FilterSheetFragment : BottomSheetDialogFragment() {
    private var _binding: FilterSheetBinding? = null
    private val binding get() = _binding!!

    private val homeVm: HomeViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FilterSheetBinding.inflate(inflater, container, false)
        .also { _binding = it }
        .root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.vm = homeVm
        binding.lifecycleOwner = viewLifecycleOwner
        val currentCategory = homeVm.uiState.value.selectedCategory

        viewLifecycleOwner.lifecycleScope.launch {
            homeVm.loadCategories()
                .collectLatest { categories ->
                    val chipGroup = binding.chipGroupCategories
                    chipGroup.removeAllViews()

                    categories.forEach { category ->
                        val chip = Chip(requireContext()).apply {
                            text = category.displayName
                            isCheckable = true
                            isChecked = (category.slug == currentCategory)
                            setOnCheckedChangeListener { _, isChecked ->
                                if (isChecked) {
                                    homeVm.updateSelectedCategory(category.slug)
                                } else {
                                    homeVm.clearFilter()
                                }
                            }
                            // Her bir çip arasında boşluk
                            setPadding(8)
                        }
                        chipGroup.addView(chip)
                    }
                }
        }

        binding.btnClearFilter.setOnClickListener {
            binding.chipGroupCategories.clearCheck()
            homeVm.clearFilter()
            dismiss()
        }

        binding.btnApply.setOnClickListener {
            homeVm.fetchByCategory(homeVm.uiState.value.selectedCategory ?: return@setOnClickListener)
            dismiss()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}