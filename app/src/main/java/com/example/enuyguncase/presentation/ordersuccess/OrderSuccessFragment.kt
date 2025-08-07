package com.example.enuyguncase.presentation.ordersuccess

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.enuyguncase.domain.usecase.cart.ClearCartUseCase
import com.example.enuyguncase.presentation.ordersuccess.screen.OrderSuccessScreen
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class OrderSuccessFragment : Fragment() {
    
    @Inject
    lateinit var clearCartUseCase: ClearCartUseCase
    
    private val args: OrderSuccessFragmentArgs by navArgs()
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ) = ComposeView(requireContext()).apply {
        setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
        setContent {
            OrderSuccessScreen(
                orderNumber = args.orderNumber,
                onContinueShopping = {
        
                    lifecycleScope.launch {
                        clearCartUseCase()
                        findNavController().navigate(
                            OrderSuccessFragmentDirections.actionOrderSuccessFragmentToHomeFragment()
                        )
                    }
                },
                onGoHome = {
                    lifecycleScope.launch {
                        clearCartUseCase()
                        findNavController().navigate(
                            OrderSuccessFragmentDirections.actionOrderSuccessFragmentToHomeFragment()
                        )
                    }
                }
            )
        }
    }
} 