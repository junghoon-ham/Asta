package com.hampson.asta.domain.model

import com.hampson.asta.domain.util.CategoryType
import com.hampson.asta.domain.util.ProductType
import com.hampson.asta.domain.util.TradeType

data class Product(
    val id: Int? = null,
    val productName: String? = null, // 상품명
    //TODO: test로 인해 int
    val productMainImage: Int? = null, // 상품 메인 이미지
    val productSubImages: List<String>? = null, // 상품 서브 이미지
    val startPrice: Int? = null, // 시작가
    val currentPrice: Int? = null, // 현재가
    val hopePrice: Int? = null, // 희망가
    val increasePrice: Int? = null, // 증액
    val deadline: String? = null, // 마감일
    val bidderCount: Int? = null, // 입찰자 수
    val status: ProductType? = null, // 상품 status
    val transaction: TradeType? = null, // 거래 방식
    val category: CategoryType? = null // 카테고리
) {
    enum class PriceType {
        START,
        CURRENT,
        HOPE,
        INCREASE;

        fun priceInfo(): ArrayList<String> {
            return when (this) {
                START -> arrayListOf(
                    "ㆍ시작가격 부터 입찰이 시작됩니다."
                )
                HOPE -> arrayListOf(
                    "ㆍ희망가격 이상으로 입찰 될 경우 바로 낙찰됩니다."
                )
                INCREASE -> arrayListOf(
                    "ㆍ입찰 시 올라가는 금액입니다."
                )
                else -> arrayListOf()
            }
        }
    }
}