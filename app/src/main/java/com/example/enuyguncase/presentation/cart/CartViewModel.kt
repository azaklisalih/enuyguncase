package com.example.enuyguncase.presentation.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.enuyguncase.domain.model.CartItem
import com.example.enuyguncase.domain.usecase.cart.CheckoutUseCase
import com.example.enuyguncase.domain.usecase.cart.DecreaseCartQuantityUseCase
import com.example.enuyguncase.domain.usecase.cart.GetCartItemsUseCase
import com.example.enuyguncase.domain.usecase.cart.IncreaseCartQuantityUseCase
import com.example.enuyguncase.domain.usecase.cart.RemoveCartItemUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val getCartItems: GetCartItemsUseCase,
    private val increaseQty: IncreaseCartQuantityUseCase,
    private val decreaseQty: DecreaseCartQuantityUseCase,
    private val removeFromCart: RemoveCartItemUseCase,
    private val checkout: CheckoutUseCase
) : ViewModel() {
    val cartItems: StateFlow<List<CartItem>> = getCartItems()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())


    fun increaseQuantity(id: Int) = viewModelScope.launch { increaseQty.invoke(id) }
    fun decreaseQuantity(id: Int) = viewModelScope.launch { decreaseQty.invoke(id) }
    fun removeFromCart(id: Int) = viewModelScope.launch { removeFromCart.invoke(id) }
    //fun checkout() = viewModelScope.launch { checkout() }
}
