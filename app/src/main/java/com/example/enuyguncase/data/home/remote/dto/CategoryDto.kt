package com.example.enuyguncase.data.home.remote.dto

import com.example.enuyguncase.domain.model.Category

data class CategoryDto(
    val slug: String,
    val name: String,
    val url: String
) {
    fun toDomain(): Category =
        Category(slug = slug, displayName = name)
}
