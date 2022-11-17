package com.hampson.asta.domain.util

enum class BidType {
    BID, // 입찰
    FAIL, // 패찰
    SUCCESS, // 낙찰
    WITHDRAW, // 철회 (판매자가 경매를 취소할때)
}