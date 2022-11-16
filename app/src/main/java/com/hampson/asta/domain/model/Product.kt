package com.hampson.asta.domain.model

import android.content.Context
import com.hampson.asta.R
import com.hampson.asta.domain.util.BidType
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
    //TODO: test이후 삭제 (statusBid)
    val statusBid: BidType? = null,
    val transaction: TradeType? = null, // 거래 방식
    val category: CategoryType? = null // 카테고리
) {
    enum class PriceType {
        START,
        CURRENT,
        HOPE,
        INCREASE;

        fun priceInfo(
            context: Context
        ): ArrayList<String> {
            return when (this) {
                START -> arrayListOf(
                    context.getString(R.string.price_info_start_1)
                )
                HOPE -> arrayListOf(
                    context.getString(R.string.price_info_hope_1)
                )
                INCREASE -> arrayListOf(
                    context.getString(R.string.price_info_increase_1)
                )
                else -> arrayListOf()
            }
        }
    }
}