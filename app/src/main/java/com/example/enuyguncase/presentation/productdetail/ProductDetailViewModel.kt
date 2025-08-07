package com.example.enuyguncase.presentation.productdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.enuyguncase.domain.model.CartItem
import com.example.enuyguncase.domain.model.Product
import com.example.enuyguncase.domain.usecase.AddToCartUseCase
import com.example.enuyguncase.domain.usecase.productdetail.AddFavoriteUseCase
import com.example.enuyguncase.domain.usecase.productdetail.CheckFavoriteUseCase
import com.example.enuyguncase.domain.usecase.productdetail.GetProductByIdUseCase
import com.example.enuyguncase.domain.usecase.productdetail.RemoveFavoriteUseCase
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
class ProductDetailViewModel @Inject constructor(
    private val getById: GetProductByIdUseCase,
    private val addFav: AddFavoriteUseCase,
    private val removeFav: RemoveFavoriteUseCase,
    private val addCart: AddToCartUseCase,
    private val checkFav: CheckFavoriteUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(ProductDetailUIState(isLoading = true))
    val uiState: StateFlow<ProductDetailUIState> = _uiState.asStateFlow()

    private val _isFav = MutableStateFlow(false)
    val isFav: StateFlow<Boolean> = _isFav.asStateFlow()

    private var currentProduct: Product? = null

    fun fetchDetail(productId: Int) {
        viewModelScope.launch {
            try {
                delay(1000)
                getById(productId)
                    .onStart { _uiState.update { it.copy(isLoading = true, error = null) } }
                    .catch { e ->
                        _uiState.update { it.copy(isLoading = false, error = e.message) }
                    }
                    .collect { prod ->
                        currentProduct = prod
                        _uiState.update { it.copy(product = prod, isLoading = false) }
                        updateFavoriteFlag(prod.id)
                    }
            } catch (exception: Exception) {
                _uiState.update { it.copy(isLoading = false, error = exception.message) }
            }
        }
    }

    private fun updateFavoriteFlag(productId: Int) = viewModelScope.launch {
        checkFav.invoke(productId).let {
            _isFav.value = it
        }
    }

    fun toggleFavorite() = viewModelScope.launch {
        currentProduct?.let { prod ->
            if (_isFav.value) {
                removeFav(prod.id)
            } else {
                addFav(prod)
            }
            _isFav.value = !_isFav.value
        }
    }

    fun addToCart(product: Product) = viewModelScope.launch {
        addCart.invoke(
            CartItem(
                productId = product.id,
                title = product.title,
                price = product.price,
                discountPrice = product.discountedPrice,
                thumbnail = product.thumbnail,
                quantity = 1
                )
        )
    }
}
