package com.example.enuyguncase.presentation.productdetail

import ProductDetailScreen
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.ViewGroup

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductDetailFragment : Fragment() {
    private val vm: ProductDetailViewModel by viewModels()
    private val args: ProductDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = ComposeView(requireContext()).apply {
        setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
        setContent {
            val productId = args.productId
            vm.fetchDetail(productId)
            val uiState by vm.uiState.collectAsState()
            val isFav by vm.isFav.collectAsState()
            ProductDetailScreen(
                uiState = uiState,
                isFavorite = isFav,
                onBack  = { findNavController().popBackStack() },
                onToggleFavorite = { vm.toggleFavorite() },
                onAddToCart      = { vm.addToCart(it) }
            )

        }
    }

}