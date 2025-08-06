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


    fun ProductDto.toDomain(): Product = Product(
        id                    = id,
        title                 = title,
        description           = description,
        category              = category,
        price                 = price,
        discountPercentage    = discountPercentage,
        rating                = rating,
        stock                 = stock,
        tags                  = tags,
        brand                 = brand ?: "Unknown",
        sku                   = sku,
        weight                = weight,
        dimensions            = dimensions.toDomain(),
        warrantyInformation   = warrantyInformation,
        shippingInformation   = shippingInformation,
        availabilityStatus    = availabilityStatus,
        reviews               = reviews.map { it.toDomain() },
        returnPolicy          = returnPolicy,
        minimumOrderQuantity  = minimumOrderQuantity,
        meta                  = meta.toDomain(),
        thumbnail             = thumbnail,
        images                = images
    )

    fun DimensionsDto.toDomain(): Dimensions = Dimensions(
        width  = width,
        height = height,
        depth  = depth
    )

    fun ReviewDto.toDomain(): Review = Review(
        rating        = rating,
        comment       = comment,
        date          = date,
        reviewerName  = reviewerName,
        reviewerEmail = reviewerEmail
    )

    fun MetaDto.toDomain(): Meta = Meta(
        createdAt = createdAt,
        updatedAt = updatedAt,
        barcode   = barcode,
        qrCode    = qrCode
    )

    fun ProductsResponse.toDomainList(): List<Product> = products.map { it.toDomain() }