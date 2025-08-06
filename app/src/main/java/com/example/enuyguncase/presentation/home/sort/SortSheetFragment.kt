package com.example.enuyguncase.presentation.home.sort

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.fragment.app.activityViewModels
import com.example.enuyguncase.databinding.SortSheetBinding
import com.example.enuyguncase.presentation.home.HomeViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class SortSheetFragment : BottomSheetDialogFragment() {

    private var _binding: SortSheetBinding? = null
    private val binding get() = _binding!!
    private val homeVm: HomeViewModel by activityViewModels()

    data class SortOption(val label: String, val sortBy: String?, val order: String?)

    private val sortOptions = listOf(
        SortOption("Varsayılan (Sıralama yok)", null, null),
        SortOption("Fiyata göre ↑",            "price",             "asc"),
        SortOption("Fiyata göre ↓",            "price",             "desc"),
        SortOption("Puanına göre ↑",           "rating",            "asc"),
        SortOption("Puanına göre ↓",           "rating",            "desc"),
        SortOption("İndirime göre ↓",          "discountPercentage","desc"),
        SortOption("Stok miktarına göre ↑",    "stock",             "asc"),
        SortOption("Stok miktarına göre ↓",    "stock",             "desc"),
        SortOption("Ada göre A→Z",             "title",             "asc"),
        SortOption("Ada göre Z→A",             "title",             "desc")
    )

    override fun onCreateView(inflater: LayoutInflater, c: ViewGroup?, s: Bundle?) =
        SortSheetBinding.inflate(inflater, c, false).also { _binding = it }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 1) RadioGroup'a dinamik RadioButton ekle
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
            if (index == 0) radio.isChecked = true  // ilkini varsayılan yap
        }

        // 2) Seçim değiştiğinde ViewModel'i tetikle
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
