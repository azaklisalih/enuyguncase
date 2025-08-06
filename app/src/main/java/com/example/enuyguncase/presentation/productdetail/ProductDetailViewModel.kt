package com.example.enuyguncase.presentation.productdetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.enuyguncase.domain.model.Product
import com.example.enuyguncase.domain.usecase.productdetail.AddCartUseCase
import com.example.enuyguncase.domain.usecase.productdetail.AddFavoriteUseCase
import com.example.enuyguncase.domain.usecase.productdetail.CheckFavoriteUseCase
import com.example.enuyguncase.domain.usecase.productdetail.GetProductByIdUseCase
import com.example.enuyguncase.domain.usecase.productdetail.RemoveFavoriteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
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
    private val addCart: AddCartUseCase,
    private val checkFav: CheckFavoriteUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(ProductDetailUIState())
    val uiState: StateFlow<ProductDetailUIState> = _uiState.asStateFlow()

    private val _isFav = MutableStateFlow(false)
    val isFav: StateFlow<Boolean> = _isFav.asStateFlow()

    private var currentProduct: Product? = null

    init {
        //fetchDetail()
    }

    fun fetchDetail(productId: Int) {
        viewModelScope.launch {
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

    fun addToCart(product: Product) = viewModelScope.launch {}
}
