package com.example.enuyguncase.data.home.mapper

import com.example.enuyguncase.data.home.remote.dto.DimensionsDto
import com.example.enuyguncase.data.home.remote.dto.MetaDto
import com.example.enuyguncase.data.home.remote.dto.ProductDto
import com.example.enuyguncase.data.home.remote.dto.ProductsResponse
import com.example.enuyguncase.data.home.remote.dto.ReviewDto
import com.example.enuyguncase.domain.model.Dimensions
import com.example.enuyguncase.domain.model.Meta
import com.example.enuyguncase.domain.model.Product
import com.example.enuyguncase.domain.model.Review
import com.example.enuyguncase.domain.model.ErrorMessages
import org.junit.Assert.assertEquals
import org.junit.Test

class ProductMapperTest {

    @Test
    fun `ProductDto toDomain should map correctly`() {
        // Given
        val productDto = ProductDto(
            id = 1,
            title = "Test Product",
            description = "Test Description",
            category = "electronics",
            price = 99.99,
            discountPercentage = 10.0,
            rating = 4.5,
            stock = 50,
            tags = listOf("electronics", "gadget"),
            brand = "Test Brand",
            sku = "TEST-001",
            weight = 500,
            dimensions = DimensionsDto(10.0, 5.0, 2.0),
            warrantyInformation = "1 year warranty",
            shippingInformation = "Free shipping",
            availabilityStatus = "In Stock",
            reviews = listOf(
                ReviewDto(4, "Great product!", "2024-01-01", "John Doe", "john@example.com")
            ),
            returnPolicy = "30 days return",
            minimumOrderQuantity = 1,
            meta = MetaDto("2024-01-01", "2024-01-01", "123456789", "QR123"),
            thumbnail = "https://example.com/thumbnail.jpg",
            images = listOf("https://example.com/image1.jpg", "https://example.com/image2.jpg")
        )

        // When
        val result = productDto.toDomain()

        // Then
        assertEquals(1, result.id)
        assertEquals("Test Product", result.title)
        assertEquals("Test Description", result.description)
        assertEquals("electronics", result.category)
        assertEquals(99.99, result.price, 0.01)
        assertEquals(10.0, result.discountPercentage, 0.01)
        assertEquals(4.5, result.rating, 0.01)
        assertEquals(50, result.stock)
        assertEquals(listOf("electronics", "gadget"), result.tags)
        assertEquals("Test Brand", result.brand)
        assertEquals("TEST-001", result.sku)
        assertEquals(500, result.weight)
        assertEquals("1 year warranty", result.warrantyInformation)
        assertEquals("Free shipping", result.shippingInformation)
        assertEquals("In Stock", result.availabilityStatus)
        assertEquals("30 days return", result.returnPolicy)
        assertEquals(1, result.minimumOrderQuantity)
        assertEquals("https://example.com/thumbnail.jpg", result.thumbnail)
        assertEquals(listOf("https://example.com/image1.jpg", "https://example.com/image2.jpg"), result.images)
    }

    @Test
    fun `ProductDto toDomain should handle null brand`() {
        // Given
        val productDto = ProductDto(
            id = 1,
            title = "Test Product",
            description = "Test Description",
            category = "electronics",
            price = 99.99,
            discountPercentage = 10.0,
            rating = 4.5,
            stock = 50,
            tags = listOf("electronics"),
            brand = null,
            sku = "TEST-001",
            weight = 500,
            dimensions = DimensionsDto(10.0, 5.0, 2.0),
            warrantyInformation = "1 year warranty",
            shippingInformation = "Free shipping",
            availabilityStatus = "In Stock",
            reviews = emptyList(),
            returnPolicy = "30 days return",
            minimumOrderQuantity = 1,
            meta = MetaDto("2024-01-01", "2024-01-01", "123456789", "QR123"),
            thumbnail = "https://example.com/thumbnail.jpg",
            images = listOf("https://example.com/image1.jpg")
        )

        // When
        val result = productDto.toDomain()

        // Then
        assertEquals(ErrorMessages.UNKNOWN_BRAND, result.brand)
    }

    @Test
    fun `DimensionsDto toDomain should map correctly`() {
        // Given
        val dimensionsDto = DimensionsDto(10.0, 5.0, 2.0)

        // When
        val result = dimensionsDto.toDomain()

        // Then
        assertEquals(10.0, result.width, 0.01)
        assertEquals(5.0, result.height, 0.01)
        assertEquals(2.0, result.depth, 0.01)
    }

    @Test
    fun `ReviewDto toDomain should map correctly`() {
        // Given
        val reviewDto = ReviewDto(
            rating = 4,
            comment = "Great product!",
            date = "2024-01-01",
            reviewerName = "John Doe",
            reviewerEmail = "john@example.com"
        )

        // When
        val result = reviewDto.toDomain()

        // Then
        assertEquals(4, result.rating)
        assertEquals("Great product!", result.comment)
        assertEquals("2024-01-01", result.date)
        assertEquals("John Doe", result.reviewerName)
        assertEquals("john@example.com", result.reviewerEmail)
    }

    @Test
    fun `MetaDto toDomain should map correctly`() {
        // Given
        val metaDto = MetaDto(
            createdAt = "2024-01-01",
            updatedAt = "2024-01-02",
            barcode = "123456789",
            qrCode = "QR123"
        )

        // When
        val result = metaDto.toDomain()

        // Then
        assertEquals("2024-01-01", result.createdAt)
        assertEquals("2024-01-02", result.updatedAt)
        assertEquals("123456789", result.barcode)
        assertEquals("QR123", result.qrCode)
    }

    @Test
    fun `ProductsResponse toDomainList should map correctly`() {
        // Given
        val productsResponse = ProductsResponse(
            products = listOf(
                ProductDto(
                    id = 1,
                    title = "Product 1",
                    description = "Description 1",
                    category = "electronics",
                    price = 99.99,
                    discountPercentage = 10.0,
                    rating = 4.5,
                    stock = 50,
                    tags = listOf("electronics"),
                    brand = "Brand 1",
                    sku = "SKU-001",
                    weight = 500,
                    dimensions = DimensionsDto(10.0, 5.0, 2.0),
                    warrantyInformation = "1 year warranty",
                    shippingInformation = "Free shipping",
                    availabilityStatus = "In Stock",
                    reviews = emptyList(),
                    returnPolicy = "30 days return",
                    minimumOrderQuantity = 1,
                    meta = MetaDto("2024-01-01", "2024-01-01", "123456789", "QR123"),
                    thumbnail = "https://example.com/thumbnail1.jpg",
                    images = listOf("https://example.com/image1.jpg")
                ),
                ProductDto(
                    id = 2,
                    title = "Product 2",
                    description = "Description 2",
                    category = "clothing",
                    price = 49.99,
                    discountPercentage = 5.0,
                    rating = 4.0,
                    stock = 25,
                    tags = listOf("clothing"),
                    brand = "Brand 2",
                    sku = "SKU-002",
                    weight = 200,
                    dimensions = DimensionsDto(5.0, 3.0, 1.0),
                    warrantyInformation = "No warranty",
                    shippingInformation = "Standard shipping",
                    availabilityStatus = "In Stock",
                    reviews = emptyList(),
                    returnPolicy = "14 days return",
                    minimumOrderQuantity = 1,
                    meta = MetaDto("2024-01-01", "2024-01-01", "987654321", "QR456"),
                    thumbnail = "https://example.com/thumbnail2.jpg",
                    images = listOf("https://example.com/image2.jpg")
                )
            ),
            total = 2,
            skip = 0,
            limit = 10
        )

        // When
        val result = productsResponse.toDomainList()

        // Then
        assertEquals(2, result.size)
        assertEquals(1, result[0].id)
        assertEquals("Product 1", result[0].title)
        assertEquals(2, result[1].id)
        assertEquals("Product 2", result[1].title)
    }

    @Test
    fun `ProductsResponse toDomainList should handle empty products list`() {
        // Given
        val productsResponse = ProductsResponse(
            products = emptyList(),
            total = 0,
            skip = 0,
            limit = 10
        )

        // When
        val result = productsResponse.toDomainList()

        // Then
        assertEquals(0, result.size)
    }

    @Test
    fun `ProductDto toDomain should calculate discounted price correctly`() {
        // Given
        val productDto = ProductDto(
            id = 1,
            title = "Test Product",
            description = "Test Description",
            category = "electronics",
            price = 100.0,
            discountPercentage = 20.0,
            rating = 4.5,
            stock = 50,
            tags = listOf("electronics"),
            brand = "Test Brand",
            sku = "TEST-001",
            weight = 500,
            dimensions = DimensionsDto(10.0, 5.0, 2.0),
            warrantyInformation = "1 year warranty",
            shippingInformation = "Free shipping",
            availabilityStatus = "In Stock",
            reviews = emptyList(),
            returnPolicy = "30 days return",
            minimumOrderQuantity = 1,
            meta = MetaDto("2024-01-01", "2024-01-01", "123456789", "QR123"),
            thumbnail = "https://example.com/thumbnail.jpg",
            images = listOf("https://example.com/image1.jpg")
        )

        // When
        val result = productDto.toDomain()

        // Then
        assertEquals(80.0, result.discountedPrice, 0.01)
    }

    @Test
    fun `ProductDto toDomain should handle zero discount percentage`() {
        // Given
        val productDto = ProductDto(
            id = 1,
            title = "Test Product",
            description = "Test Description",
            category = "electronics",
            price = 100.0,
            discountPercentage = 0.0,
            rating = 4.5,
            stock = 50,
            tags = listOf("electronics"),
            brand = "Test Brand",
            sku = "TEST-001",
            weight = 500,
            dimensions = DimensionsDto(10.0, 5.0, 2.0),
            warrantyInformation = "1 year warranty",
            shippingInformation = "Free shipping",
            availabilityStatus = "In Stock",
            reviews = emptyList(),
            returnPolicy = "30 days return",
            minimumOrderQuantity = 1,
            meta = MetaDto("2024-01-01", "2024-01-01", "123456789", "QR123"),
            thumbnail = "https://example.com/thumbnail.jpg",
            images = listOf("https://example.com/image1.jpg")
        )

        // When
        val result = productDto.toDomain()

        // Then
        assertEquals(100.0, result.discountedPrice, 0.01)
    }
} 