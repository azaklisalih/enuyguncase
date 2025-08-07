package com.example.enuyguncase.data.home.repository

import com.example.enuyguncase.data.home.mapper.toDomain
import com.example.enuyguncase.data.home.remote.api.ProductApi
import com.example.enuyguncase.data.home.remote.dto.ProductsResponse
import com.example.enuyguncase.domain.model.Category
import com.example.enuyguncase.domain.model.Product
import com.example.enuyguncase.domain.model.ErrorMessages
import com.example.enuyguncase.domain.repository.ProductRepository
import com.google.ai.client.generativeai.common.ServerException
import com.google.assistant.appactions.suggestions.client.ClientException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductRepositoryImpl @Inject constructor(
    private val api: ProductApi
) : ProductRepository {

    override fun getProductsFlow(
        limit: Int?,
        skip: Int?,
        sortBy: String?,
        order: String?
    ): Flow<ProductsResponse> = flow {
        val response = api.getProducts(limit, skip, sortBy, order)
        when (response.code()) {
            in 200..299 -> {
                val body = response.body()
                    ?: throw Exception(ErrorMessages.EMPTY_RESPONSE)
                emit(body)
            }

            in 400..499 -> {
                throw ClientException(response.message() ?: ErrorMessages.CLIENT_ERROR)
            }

            in 500..599 -> {
                throw ServerException(response.message() ?: ErrorMessages.SERVER_ERROR)
            }

            else -> {
                throw Exception("${ErrorMessages.UNEXPECTED_HTTP}: ${response.code()}")
            }
        }
    }.flowOn(Dispatchers.IO)

    override fun getProductByIdFlow(id: Int): Flow<Product> = flow {
        val response = api.getProductById(id)
        when (response.code()) {
            in 200..299 -> {
                val body = response.body() ?: throw Exception(ErrorMessages.EMPTY_RESPONSE)
                emit(body.toDomain())
            }

            in 400..499 -> throw Exception("${ErrorMessages.CLIENT_ERROR}: ${response.message()}")
            in 500..599 -> throw Exception("${ErrorMessages.SERVER_ERROR}: ${response.message()}")
            else -> throw Exception("${ErrorMessages.UNEXPECTED_HTTP}: ${response.code()}")
        }
    }.flowOn(Dispatchers.IO)

    override fun searchProductsFlow(query: String): Flow<ProductsResponse> = flow {
        val response = api.searchProducts(query)
        when (response.code()) {
            in 200..299 -> {
                val body = response.body() ?: throw Exception(ErrorMessages.EMPTY_RESPONSE)
                emit(body)
            }

            in 400..499 -> throw Exception("${ErrorMessages.CLIENT_ERROR}: ${response.message()}")
            in 500..599 -> throw Exception("${ErrorMessages.SERVER_ERROR}: ${response.message()}")
            else -> throw Exception("${ErrorMessages.UNEXPECTED_HTTP}: ${response.code()}")
        }
    }.flowOn(Dispatchers.IO)

    override fun getCategoriesFlow(): Flow<List<Category>> = flow {
        val response = api.getCategories()
        when (response.code()) {
            in 200..299 -> {
                val body = response.body() ?: throw Exception(ErrorMessages.EMPTY_RESPONSE)
                emit(body.map { it.toDomain() })
            }

            in 400..499 -> throw Exception("${ErrorMessages.CLIENT_ERROR}: ${response.message()}")
            in 500..599 -> throw Exception("${ErrorMessages.SERVER_ERROR}: ${response.message()}")
            else -> throw Exception("${ErrorMessages.UNEXPECTED_HTTP}: ${response.code()}")
        }
    }.flowOn(Dispatchers.IO)

    override fun getProductsByCategoryFlow(
        category: String,
        limit: Int?,
        skip: Int?,
        sortBy: String?,
        order: String?
    ): Flow<ProductsResponse> = flow {
        val response = api.getProductsByCategory(category, limit, skip)
        when (response.code()) {
            in 200..299 -> emit(
                response.body()
                    ?: throw Exception(ErrorMessages.EMPTY_RESPONSE)
            )

            in 400..499 -> throw Exception("${ErrorMessages.CLIENT_ERROR}: ${response.message()}")
            in 500..599 -> throw Exception("${ErrorMessages.SERVER_ERROR}: ${response.message()}")
            else -> throw Exception("${ErrorMessages.UNEXPECTED_HTTP}: ${response.code()}")
        }
    }.flowOn(Dispatchers.IO)

}
