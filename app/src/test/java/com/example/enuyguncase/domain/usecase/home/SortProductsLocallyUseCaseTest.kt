package com.example.enuyguncase.domain.usecase.home

import com.example.enuyguncase.domain.model.Product
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class SortProductsLocallyUseCaseTest {

    private lateinit var useCase: SortProductsLocallyUseCase
    private lateinit var testProducts: List<Product>

    @Before
    fun setup() {
        useCase = SortProductsLocallyUseCase()
        
        testProducts = listOf(
            Product(
                id = 1,
                title = "iPhone 12",
                description = "Apple iPhone 12",
                price = 999.0,
                discountPercentage = 10.0,
                rating = 4.5,
                stock = 50,
                brand = "Apple",
                category = "smartphones",
                thumbnail = "thumbnail1.jpg",
                images = listOf("image1.jpg"),
                tags = listOf("smartphone", "apple"),
                sku = "IPHONE12",
                weight = 164.0,
                dimensions = null,
                warrantyInformation = "1 year warranty",
                shippingInformation = "Free shipping",
                availabilityStatus = "In Stock",
                reviews = emptyList(),
                returnPolicy = "30 days return",
                minimumOrderQuantity = 1,
                meta = null
            ),
            Product(
                id = 2,
                title = "Samsung Galaxy S21",
                description = "Samsung Galaxy S21",
                price = 799.0,
                discountPercentage = 15.0,
                rating = 4.3,
                stock = 30,
                brand = "Samsung",
                category = "smartphones",
                thumbnail = "thumbnail2.jpg",
                images = listOf("image2.jpg"),
                tags = listOf("smartphone", "samsung"),
                sku = "SAMSUNG21",
                weight = 169.0,
                dimensions = null,
                warrantyInformation = "1 year warranty",
                shippingInformation = "Free shipping",
                availabilityStatus = "In Stock",
                reviews = emptyList(),
                returnPolicy = "30 days return",
                minimumOrderQuantity = 1,
                meta = null
            ),
            Product(
                id = 3,
                title = "Google Pixel 5",
                description = "Google Pixel 5",
                price = 699.0,
                discountPercentage = 5.0,
                rating = 4.7,
                stock = 20,
                brand = "Google",
                category = "smartphones",
                thumbnail = "thumbnail3.jpg",
                images = listOf("image3.jpg"),
                tags = listOf("smartphone", "google"),
                sku = "PIXEL5",
                weight = 151.0,
                dimensions = null,
                warrantyInformation = "1 year warranty",
                shippingInformation = "Free shipping",
                availabilityStatus = "In Stock",
                reviews = emptyList(),
                returnPolicy = "30 days return",
                minimumOrderQuantity = 1,
                meta = null
            )
        )
    }

    @Test
    fun `invoke should return original list when sortBy is null`() {
        val result = useCase(testProducts, null, null)
        assertEquals(testProducts, result)
    }

    @Test
    fun `invoke should sort by price ascending`() {
        val result = useCase(testProducts, "price", "asc")
        
        assertEquals(3, result.size)
        assertEquals("Google Pixel 5", result[0].title) // 699.0
        assertEquals("Samsung Galaxy S21", result[1].title) // 799.0
        assertEquals("iPhone 12", result[2].title) // 999.0
    }

    @Test
    fun `invoke should sort by price descending`() {
        val result = useCase(testProducts, "price", "desc")
        
        assertEquals(3, result.size)
        assertEquals("iPhone 12", result[0].title) // 999.0
        assertEquals("Samsung Galaxy S21", result[1].title) // 799.0
        assertEquals("Google Pixel 5", result[2].title) // 699.0
    }

    @Test
    fun `invoke should sort by rating ascending`() {
        val result = useCase(testProducts, "rating", "asc")
        
        assertEquals(3, result.size)
        assertEquals("Samsung Galaxy S21", result[0].title) // 4.3
        assertEquals("iPhone 12", result[1].title) // 4.5
        assertEquals("Google Pixel 5", result[2].title) // 4.7
    }

    @Test
    fun `invoke should sort by rating descending`() {
        val result = useCase(testProducts, "rating", "desc")
        
        assertEquals(3, result.size)
        assertEquals("Google Pixel 5", result[0].title) // 4.7
        assertEquals("iPhone 12", result[1].title) // 4.5
        assertEquals("Samsung Galaxy S21", result[2].title) // 4.3
    }

    @Test
    fun `invoke should sort by discount percentage descending`() {
        val result = useCase(testProducts, "discountPercentage", "desc")
        
        assertEquals(3, result.size)
        assertEquals("Samsung Galaxy S21", result[0].title) // 15.0%
        assertEquals("iPhone 12", result[1].title) // 10.0%
        assertEquals("Google Pixel 5", result[2].title) // 5.0%
    }

    @Test
    fun `invoke should sort by stock ascending`() {
        val result = useCase(testProducts, "stock", "asc")
        
        assertEquals(3, result.size)
        assertEquals("Google Pixel 5", result[0].title) // 20
        assertEquals("Samsung Galaxy S21", result[1].title) // 30
        assertEquals("iPhone 12", result[2].title) // 50
    }

    @Test
    fun `invoke should sort by title alphabetically`() {
        val result = useCase(testProducts, "title", "asc")
        
        assertEquals(3, result.size)
        assertEquals("Google Pixel 5", result[0].title)
        assertEquals("iPhone 12", result[1].title)
        assertEquals("Samsung Galaxy S21", result[2].title)
    }

    @Test
    fun `invoke should sort by title reverse alphabetically`() {
        val result = useCase(testProducts, "title", "desc")
        
        assertEquals(3, result.size)
        assertEquals("Samsung Galaxy S21", result[0].title)
        assertEquals("iPhone 12", result[1].title)
        assertEquals("Google Pixel 5", result[2].title)
    }

    @Test
    fun `invoke should return original list for unknown sortBy`() {
        val result = useCase(testProducts, "unknown", "asc")
        assertEquals(testProducts, result)
    }
} 