package com.hampson.asta.domain.util

import android.content.Context
import com.hampson.asta.R

enum class AuctionType {
    BIDDING,
    WITHDRAW,
    FAIL,
    SUCCESS,
    END,
    NONE;

    fun typeName(context: Context?): String {
        return when (this) {
            BIDDING -> context?.getString(R.string.auction_bidding) ?: ""
            WITHDRAW -> context?.getString(R.string.auction_withdraw) ?: ""
            FAIL -> context?.getString(R.string.auction_fail) ?: ""
            SUCCESS -> context?.getString(R.string.auction_success) ?: ""
            END -> context?.getString(R.string.auction_end) ?: ""
            else -> ""
        }
    }

    fun typeDrawable(): Int {
        return when (this) {
            BIDDING -> R.drawable.circle_auction_bidding_type
            WITHDRAW -> R.drawable.circle_auction_withdraw_type
            FAIL -> R.drawable.circle_auction_fail_type
            SUCCESS -> R.drawable.circle_auction_success_type
            END -> R.drawable.circle_auction_end_type
            else -> 0
        }
    }

    fun typeColor(): Int {
        return when (this) {
            BIDDING -> R.color.color_47C832
            WITHDRAW -> R.color.color_A6A6A6
            FAIL -> R.color.color_747474
            SUCCESS -> R.color.color_F15F5F
            END -> R.color.color_3162C7
            else -> R.color.color_F15F5F
        }
    }

    fun actionButtonMessage(context: Context?): String {
        return when (this) {
            BIDDING -> context?.getString(R.string.bidding) ?: ""
            WITHDRAW -> context?.getString(R.string.action_withdraw_message) ?: ""
            FAIL -> context?.getString(R.string.action_fail_message) ?: ""
            SUCCESS -> context?.getString(R.string.action_success_message) ?: ""
            END -> context?.getString(R.string.action_end_message) ?: ""
            else -> context?.getString(R.string.bidding) ?: ""
        }
    }
}