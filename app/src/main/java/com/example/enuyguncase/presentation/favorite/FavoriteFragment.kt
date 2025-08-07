package com.example.enuyguncase.presentation.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.viewModels
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.fragment.findNavController
import com.example.enuyguncase.presentation.common.navigation.NavigationRouter
import com.example.enuyguncase.presentation.favorite.screen.FavoriteScreen
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FavoriteFragment : Fragment() {
    private val vm: FavoriteViewModel by viewModels()
    
    @Inject
    lateinit var navigationRouter: NavigationRouter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = ComposeView(requireContext()).apply {
        setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
        setBackgroundColor(android.graphics.Color.WHITE)
        setContent {
            val uiState by vm.uiState.collectAsStateWithLifecycle()

            FavoriteScreen(
                products = uiState.favorites,
                onAddToCart = { favorite ->
                    vm.addToCart(favorite)
                },
                onRemove = { vm.onRemove(it) },
                onItemClick = { productId ->
                    navigationRouter.navigateToProductDetail(findNavController(), productId)
                },
                isLoading = uiState.isLoading
            )
        }
    }
}