package com.hampson.asta.domain.util

import android.content.Context
import com.hampson.asta.R

enum class TradeType {
    PARCEL,
    DIRECT_TRANSACTION,
    NEGOTIABLE;

    fun tradeName(context: Context): String {
        return when (this) {
            PARCEL -> context.getString(R.string.trade_parcel)
            DIRECT_TRANSACTION -> context.getString(R.string.trade_direct_transaction)
            NEGOTIABLE -> context.getString(R.string.trade_negotiable)
        }
    }
}