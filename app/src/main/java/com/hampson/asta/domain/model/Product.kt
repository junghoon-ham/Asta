package com.hampson.asta.domain.model

import com.hampson.asta.domain.util.CategoryType
import com.hampson.asta.domain.util.ProductType
import com.hampson.asta.domain.util.TransactionType

data class Product(
    val id: Int? = null,
    val productName: String? = null, // 상품명
    val productMainImage: String? = null, // 상품 메인 이미지
    val productSubImages: List<String>? = null, // 상품 서브 이미지
    val startPrice: Int? = null, // 시작가
    val currentPrice: Int? = null, // 현재가
    val hopePrice: Int? = null, // 희망가
    val increasePrice: Int? = null, // 증액
    val deadline: String? = null, // 마감일
    val bidderCount: Int? = null, // 입찰자 수
    val status: ProductType? = null, // 상품 status
    val transaction: TransactionType? = null, // 거래 방식
    val category: CategoryType? = null // 카테고리
)