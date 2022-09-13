package com.hampson.asta.domain.model

data class Product(
    val id: Int,
    val productName: String? = "",
    val productMainImage: String? = "",
    val startPrice: Int? = 0,
    val currentPrice: Int? = 0,
    val bidderCount: Int? = 0
)