package com.example.enuyguncase.presentation.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.enuyguncase.domain.model.CartItem
import com.example.enuyguncase.domain.usecase.AddToCartUseCase
import com.example.enuyguncase.domain.model.Favorite
import com.example.enuyguncase.domain.usecase.favorite.GetAllFavoritesUseCase
import com.example.enuyguncase.domain.usecase.productdetail.RemoveFavoriteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val getAllFavorites: GetAllFavoritesUseCase,
    private val removeFavorite: RemoveFavoriteUseCase,
    private val addToCart: AddToCartUseCase
) : ViewModel() {




    val favorites: StateFlow<List<Favorite>> = getAllFavorites()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList()
        )


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
