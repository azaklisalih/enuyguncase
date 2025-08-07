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
        // ViewLifecycle ile uyumlu temizleme
        setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)

        setContent {
            // 1) Flow<List<CartItem>> → State<List<CartItem>>
            val items by vm.cartItems.collectAsState(initial = emptyList())

            // 2) Sepet ekranını çiz
            CartScreen(
                cartItems = items,
                onIncrease = { vm.increaseQuantity(it) },
                onDecrease = { vm.decreaseQuantity(it) },
                onRemove = { vm.removeFromCart(it) },
                onCheckout = { findNavController().navigate(CartFragmentDirections.actionCartFragmentToCheckoutFragment()) }
            )
        }
    }
}
