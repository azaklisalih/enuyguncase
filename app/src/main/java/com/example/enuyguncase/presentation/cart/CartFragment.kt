package com.example.enuyguncase.presentation.cart

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
import com.example.enuyguncase.presentation.cart.screen.CartScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CartFragment : Fragment() {
    private val vm: CartViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = ComposeView(requireContext()).apply {

        setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)

        setContent {
            val uiState by vm.uiState.collectAsState()
            CartScreen(
                cartItems = uiState.cartItems,
                onIncrease = { vm.increaseQuantity(it) },
                onDecrease = { vm.decreaseQuantity(it) },
                onRemove = { vm.removeFromCart(it) },
                onCheckout = { findNavController().navigate(CartFragmentDirections.actionCartFragmentToCheckoutFragment()) },
                isLoading = uiState.isLoading
            )
        }
    }
}
