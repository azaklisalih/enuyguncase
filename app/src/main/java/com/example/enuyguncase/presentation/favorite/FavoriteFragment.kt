package com.example.enuyguncase.presentation.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.enuyguncase.R
import com.example.enuyguncase.presentation.favorite.screen.FavoriteScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteFragment : Fragment() {
    private val vm: FavoriteViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = ComposeView(requireContext()).apply {
        setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
        setContent {
            val favorites by vm.favorites.collectAsState()

            FavoriteScreen(
                products = favorites,
                onAddToCart = { favorite ->
                    vm.addToCart(favorite)
                },
                onRemove = { vm.onRemove(it) },
                onItemClick = { productId ->
                    findNavController().navigate(
                        FavoriteFragmentDirections.actionFavoriteFragmentToProductDetailFragment(
                            productId
                        )
                    )
                }
            )
        }
    }
}