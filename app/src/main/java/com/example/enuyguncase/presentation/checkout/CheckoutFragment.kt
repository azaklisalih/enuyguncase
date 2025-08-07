package com.example.enuyguncase.presentation.checkout

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.navigation.fragment.findNavController
import com.example.enuyguncase.presentation.checkout.screen.CheckoutScreen
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CheckoutFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ) = ComposeView(requireContext()).apply {
        setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
        setContent {
    
            var name by remember { mutableStateOf("") }
            var email by remember { mutableStateOf("") }
            var phone by remember { mutableStateOf("") }

            CheckoutScreen(
                name = name,
                email = email,
                phone = phone,
                onNameChange  = { name = it },
                onEmailChange = { email = it },
                onPhoneChange = { phone = it },
                onBack = { findNavController().popBackStack() },
                onPay  = {
        
                    val orderNumber = "ORD-${System.currentTimeMillis()}"
                    

                    findNavController().navigate(
                        CheckoutFragmentDirections.actionCheckoutFragmentToOrderSuccessFragment(orderNumber)
                    )
                }
            )
        }
    }



}