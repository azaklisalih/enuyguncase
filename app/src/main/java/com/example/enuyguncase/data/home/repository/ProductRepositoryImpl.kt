package com.example.enuyguncase.data.home.repository

import com.example.enuyguncase.data.home.mapper.toDomain
import com.example.enuyguncase.data.home.mapper.toDomainList
import com.example.enuyguncase.data.home.remote.api.ProductApi
import com.example.enuyguncase.domain.model.Product
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
    ): Flow<List<Product>> = flow {
        val response = api.getProducts(limit, skip, sortBy, order)
        // 2) HTTP koduna göre branch
        when (response.code()) {
            in 200..299 -> {
                val body = response.body()
                    ?: throw Exception("Sunucudan boş yanıt geldi")
                // 3) Domain model listesine dönüştür ve emit et
                emit(body.toDomainList())
            }

            in 400..499 -> {
                throw ClientException(response.message() ?: "İstemci hatası")
            }

            in 500..599 -> {
                throw ServerException(response.message() ?: "Sunucu hatası")
            }

            else -> {
                throw Exception("Beklenmeyen HTTP kodu: ${response.code()}")
            }
        }
    }.flowOn(Dispatchers.IO)

    override fun getProductByIdFlow(id: Int): Flow<Product> = flow {
        val response = api.getProductById(id)
        when (response.code()) {
            in 200..299 -> {
                val body = response.body() ?: throw Exception("Sunucudan boş yanıt geldi")
                emit(body.toDomain())
            }

            in 400..499 -> throw Exception("İstemci hatası: ${response.message()}")
            in 500..599 -> throw Exception("Sunucu hatası: ${response.message()}")
            else -> throw Exception("Beklenmeyen HTTP kodu: ${response.code()}")
        }
    }.flowOn(Dispatchers.IO)

    override fun searchProductsFlow(query: String): Flow<List<Product>> = flow {
        val response = api.searchProducts(query)
        when (response.code()) {
            in 200..299 -> {
                val body = response.body() ?: throw Exception("Sunucudan boş yanıt geldi")
                emit(body.toDomainList())
            }

            in 400..499 -> throw Exception("İstemci hatası: ${response.message()}")
            in 500..599 -> throw Exception("Sunucu hatası: ${response.message()}")
            else -> throw Exception("Beklenmeyen HTTP kodu: ${response.code()}")
        }
    }.flowOn(Dispatchers.IO)

    override fun getCategoriesFlow(): Flow<List<String>> = flow {
        val response = api.getCategories()
        when (response.code()) {
            in 200..299 -> {
                val body =
                    response.body() ?: throw Exception("Sunucudan boş kategori listesi geldi")
                emit(body)
            }

            in 400..499 -> throw Exception("İstemci hatası: ${response.message()}")
            in 500..599 -> throw Exception("Sunucu hatası: ${response.message()}")
            else -> throw Exception("Beklenmeyen HTTP kodu: ${response.code()}")
        }
    }.flowOn(Dispatchers.IO)

    override fun getProductsByCategoryFlow(
        category: String,
        limit: Int?,
        skip: Int?
    ): Flow<List<Product>> = flow {
        val response = api.getProductsByCategory(category, limit, skip)
        when (response.code()) {
            in 200..299 -> emit(
                response.body()?.toDomainList()
                    ?: throw Exception("Sunucudan boş yanıt geldi")
            )

            in 400..499 -> throw Exception("İstemci hatası: ${response.message()}")
            in 500..599 -> throw Exception("Sunucu hatası: ${response.message()}")
            else -> throw Exception("Beklenmeyen HTTP kodu: ${response.code()}")
        }
    }.flowOn(Dispatchers.IO)

}
