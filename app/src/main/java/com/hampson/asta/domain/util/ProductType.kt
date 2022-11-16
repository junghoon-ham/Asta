package com.hampson.asta.domain.util

enum class ProductType {
    BID, // 입찰중
    SUCCESS, // 낙찰
    WITHDRAW, // 철회 (판매자가 경매를 취소할때)
    PASSED // 유찰 (입찰자가 아무도 없을때)
}