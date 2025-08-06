package com.example.enuyguncase.presentation.home

import com.example.enuyguncase.domain.usecase.home.GetCategoriesUseCase
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.enuyguncase.domain.usecase.home.GetProductsByCategoryUseCase
import com.example.enuyguncase.domain.usecase.home.GetProductsUseCase
import com.example.enuyguncase.domain.usecase.home.SearchProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getProducts: GetProductsUseCase,
    private val getByCategory: GetProductsByCategoryUseCase,
    private val searchProducts: SearchProductsUseCase,
    private val getCategories: GetCategoriesUseCase
): ViewModel() {

    private val _uiState = MutableStateFlow(HomeUIState(isLoading = true))
    val uiState: StateFlow<HomeUIState> = _uiState.asStateFlow()

    val searchQuery = MutableLiveData<String>("")

    val totalCountText = _uiState
        .map { "(Toplam ${it.products.size} adet)" }
        .asLiveData()

    init {
        fetchProducts()
    }

     fun fetchProducts(
         limit: Int? = null,
         skip: Int? = null,
         sortBy: String? = null,
         order: String? = null
     ) {
       viewModelScope.launch {
           getProducts.invoke(limit, skip, sortBy, order).onStart {
               _uiState.value = HomeUIState(isLoading = true)
           }.catch {
               _uiState.value = HomeUIState(error = it.message)
           }.collect { products ->
               _uiState.value = HomeUIState(products = products)
           }
       }

    }

    fun fetchByCategory(
        category: String,
        limit: Int? = null,
        skip: Int? = null
    ) {
        viewModelScope.launch {
            getByCategory.invoke(category, limit, skip)
                .onStart {
                    _uiState.update { it.copy(isLoading = true, error = null) }
                }
                .catch { e ->
                    _uiState.update { it.copy(isLoading = false, error = e.message) }
                }
                .collect { list ->
                    _uiState.update { it.copy(products = list, isLoading = false) }
                }
        }
    }

    fun searchProducts(query: String) {
        viewModelScope.launch {
            searchProducts.invoke(query)
                .onStart {
                    _uiState.update { it.copy(isLoading = true, error = null) }
                }
                .catch { e ->
                    _uiState.update { it.copy(isLoading = false, error = e.message) }
                }
                .collect { list ->
                    _uiState.update { it.copy(products = list, isLoading = false) }
                }
        }
    }

    fun loadCategories(): Flow<List<String>> = getCategories.invoke()


}