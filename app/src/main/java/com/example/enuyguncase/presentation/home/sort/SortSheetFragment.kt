package com.example.enuyguncase.presentation.home.sort

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.fragment.app.activityViewModels
import com.example.enuyguncase.R
import com.example.enuyguncase.databinding.SortSheetBinding
import com.example.enuyguncase.presentation.home.HomeViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class SortSheetFragment : BottomSheetDialogFragment() {

    private var _binding: SortSheetBinding? = null
    private val binding get() = _binding!!
    private val homeVm: HomeViewModel by activityViewModels()

    data class SortOption(val label: String, val sortBy: String?, val order: String?)

    private fun getSortOptions(): List<SortOption> = listOf(
        SortOption(getString(R.string.sort_default), null, null),
        SortOption(getString(R.string.sort_price_low_to_high), "price", "asc"),
        SortOption(getString(R.string.sort_price_high_to_low), "price", "desc"),
        SortOption(getString(R.string.sort_rating_asc), "rating", "asc"),
        SortOption(getString(R.string.sort_rating_desc), "rating", "desc"),
        SortOption(getString(R.string.sort_discount_desc), "discountPercentage", "desc"),
        SortOption(getString(R.string.sort_stock_asc), "stock", "asc"),
        SortOption(getString(R.string.sort_stock_desc), "stock", "desc"),
        SortOption(getString(R.string.sort_name_a_to_z), "title", "asc"),
        SortOption(getString(R.string.sort_name_z_to_a), "title", "desc")
    )

    override fun onCreateView(inflater: LayoutInflater, c: ViewGroup?, s: Bundle?) =
        SortSheetBinding.inflate(inflater, c, false).also { _binding = it }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        
        val sortOptions = getSortOptions()
        sortOptions.forEachIndexed { index, option ->
            val radio = RadioButton(requireContext()).apply {
                id = View.generateViewId()
                text = option.label
                layoutParams = RadioGroup.LayoutParams(
                    RadioGroup.LayoutParams.MATCH_PARENT,
                    RadioGroup.LayoutParams.WRAP_CONTENT
                )
                if (option.sortBy == homeVm.uiState.value.selectedSortBy && option.order == homeVm.uiState.value.selectedSortOrder) {
                    isChecked = true
                }
            }
            binding.rgSort.addView(radio)
            if (index == 0) radio.isChecked = true
        }

        
        binding.rgSort.setOnCheckedChangeListener { _, checkedId ->
            val selected = sortOptions[binding.rgSort.indexOfChild(
                binding.rgSort.findViewById(checkedId)
            )]

            homeVm.sortProducts(
                sortBy = selected.sortBy,
                order  = selected.order
            )

            dismiss()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
