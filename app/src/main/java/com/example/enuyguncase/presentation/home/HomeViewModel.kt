package com.example.enuyguncase.presentation.home

import com.example.enuyguncase.domain.usecase.home.GetCategoriesUseCase
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.enuyguncase.domain.model.Category
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
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUIState(isLoading = true))
    val uiState: StateFlow<HomeUIState> = _uiState.asStateFlow()

    val searchQuery = MutableLiveData("")

    val totalCountText = _uiState
        .map { "(Toplam ${it.total} adet)" }
        .asLiveData()

    init {
        fetchProducts()
    }

    private fun loadPage(
        category: String? = uiState.value.selectedCategory,
        limit: Int = uiState.value.pageSize,
        skip: Int = uiState.value.page * uiState.value.pageSize,
        sortBy: String? = uiState.value.selectedSortBy,
        order: String? = uiState.value.selectedSortOrder
    ) {
        viewModelScope.launch {
            val flow = if (category == null) {
                getProducts(limit, skip, sortBy, order)
            } else {
                getByCategory(category, limit, skip)
            }
            flow.onStart {
                _uiState.update { it.copy(isLoading = true, error = null) }
            }.catch {
                _uiState.update { it.copy(isLoading = false, error = it.error) }
            }.collect { page ->
                _uiState.update { prev ->
                    val combined = if (skip == 0) {
                        page.products
                    } else {
                        prev.products + page.products
                    }
                    val newPage = if (skip == 0) 0 else prev.page + 1
                    prev.copy(
                        products = combined,
                        isLoading = false,
                        total = page.total,
                        page = newPage,
                        selectedCategory = category,
                        selectedSortBy = sortBy,
                        selectedSortOrder = order
                    )
                }
            }
        }
    }

    private fun fetchProducts() = loadPage(category = null)
    fun sortProducts(sortBy: String?, order: String?) = loadPage(sortBy = sortBy, order = order)

    fun fetchByCategory(category: String) = loadPage(category = category)

    fun searchProducts(query: String) {
        viewModelScope.launch {
            searchProducts.invoke(query)
                .onStart {
                    _uiState.update { it.copy(isLoading = true, error = null) }
                }
                .catch { e ->
                    _uiState.update { it.copy(isLoading = false, error = e.message) }
                }
                .collect { page ->
                    _uiState.update {
                        it.copy(
                            products = page.products,
                            total = page.total,
                            page = 0,
                            selectedCategory = null,
                            selectedSortBy = null,
                            selectedSortOrder = null,
                            isLoading = false
                        )
                    }
                }
        }
    }

    fun loadCategories(): Flow<List<Category>> = getCategories.invoke()

    fun loadNextPage() {
        val s = uiState.value
        if (s.isLoading || s.products.size >= s.total) return
        loadPage(
            category = s.selectedCategory,
            skip = (s.page + 1) * s.pageSize
        )
    }

    fun clearFilter() {
        _uiState.update {
            it.copy(
                selectedCategory = null,
                selectedSortBy = null,
                selectedSortOrder = null
            )
        }
        fetchProducts()
    }
    fun updateSelectedCategory(category: String) {
        _uiState.update { it.copy(selectedCategory = category) }
    }
}