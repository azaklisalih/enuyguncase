package com.example.enuyguncase.presentation.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.enuyguncase.domain.model.CartItem
import com.example.enuyguncase.domain.usecase.AddToCartUseCase
import com.example.enuyguncase.domain.model.Favorite
import com.example.enuyguncase.domain.usecase.favorite.GetAllFavoritesUseCase
import com.example.enuyguncase.domain.usecase.productdetail.RemoveFavoriteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val getAllFavorites: GetAllFavoritesUseCase,
    private val removeFavorite: RemoveFavoriteUseCase,
    private val addToCart: AddToCartUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(FavoriteUIState(isLoading = true))
    val uiState: StateFlow<FavoriteUIState> = _uiState

    init {
        loadFavorites()
    }

    private fun loadFavorites() {
        viewModelScope.launch {
            try {
                delay(1000)
                
                getAllFavorites().collect { favorites ->
                    _uiState.value = FavoriteUIState(
                        favorites = favorites,
                        isLoading = false,
                        error = null
                    )
                }
            } catch (exception: Exception) {
                _uiState.value = FavoriteUIState(
                    favorites = emptyList(),
                    isLoading = false,
                    error = exception.message ?: "Unknown error occurred"
                )
            }
        }
    }

    fun onRemove(productId: Int) = viewModelScope.launch {
        removeFavorite(productId)
    }

    fun addToCart(favorite: Favorite) = viewModelScope.launch {
        addToCart(cartItem = CartItem(
            productId = favorite.productId,
            title = favorite.title,
            price = favorite.price,
            discountPrice = favorite.discountedPrice,
            thumbnail = favorite.thumbnailUrl,
            quantity = 1
        ))
    }
}
