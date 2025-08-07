package com.example.enuyguncase.presentation.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.enuyguncase.domain.usecase.cart.DecreaseCartQuantityUseCase
import com.example.enuyguncase.domain.usecase.cart.GetCartItemsUseCase
import com.example.enuyguncase.domain.usecase.cart.IncreaseCartQuantityUseCase
import com.example.enuyguncase.domain.usecase.cart.RemoveCartItemUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val getCartItems: GetCartItemsUseCase,
    private val increaseQty: IncreaseCartQuantityUseCase,
    private val decreaseQty: DecreaseCartQuantityUseCase,
    private val removeFromCart: RemoveCartItemUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(CartUIState(isLoading = true))
    val uiState: StateFlow<CartUIState> = _uiState.asStateFlow()

    init {
        loadCartItems()
    }

    private fun loadCartItems() {
        viewModelScope.launch {
            try {
                delay(1000)
                getCartItems()
                    .onStart { _uiState.update { it.copy(isLoading = true, error = null) } }
                    .catch { e ->
                        _uiState.update { it.copy(isLoading = false, error = e.message) }
                    }
                    .collect { cartItems ->
                        _uiState.update { it.copy(cartItems = cartItems, isLoading = false) }
                    }
            } catch (exception: Exception) {
                _uiState.update { it.copy(isLoading = false, error = exception.message) }
            }
        }
    }

    fun increaseQuantity(id: Int) = viewModelScope.launch { increaseQty.invoke(id) }
    fun decreaseQuantity(id: Int) = viewModelScope.launch { decreaseQty.invoke(id) }
    fun removeFromCart(id: Int) = viewModelScope.launch { removeFromCart.invoke(id) }
}
