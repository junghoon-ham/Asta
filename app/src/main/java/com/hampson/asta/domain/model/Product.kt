package com.hampson.asta.domain.model

import com.hampson.asta.domain.util.CategoryType
import com.hampson.asta.domain.util.ProductType
import com.hampson.asta.domain.util.TransactionType

data class Product(
    val id: Int,
    val productName: String? = "", // 상품명
    val productMainImage: String? = "", // 상품 메인 이미지
    val productSubImages: List<String>?, // 상품 서브 이미지
    val startPrice: Int? = 0, // 시작가
    val currentPrice: Int? = 0, // 현재가
    val hopePrice: Int? = 0, // 희망가
    val increasePrice: Int? = 0, // 증액
    val deadline: String? = "", // 마감일
    val bidderCount: Int? = 0, // 입찰자 수
    val status: ProductType, // 상품 status
    val transaction: TransactionType, // 거래 방식
    val category: CategoryType // 카테고리
)