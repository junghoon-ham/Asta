package com.hampson.asta.domain.util

enum class TradeType(
    val tradeName: String
) {
    PARCEL("택배"),
    DIRECT_TRANSACTION("직거래"),
    NEGOTIABLE("택배 | 직거래");
}