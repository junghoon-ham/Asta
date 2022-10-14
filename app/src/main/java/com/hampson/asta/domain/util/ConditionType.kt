package com.hampson.asta.domain.util

import android.content.Context
import com.hampson.asta.R

enum class ConditionType {
    NEW,
    BEST,
    GOOD,
    USUALLY,
    LIMITED_EDITION,
    COLLECTIBLE;

    fun conditionName(context: Context): String {
        return when (this) {
            NEW -> context.getString(R.string.condition_new)
            BEST -> context.getString(R.string.condition_best)
            GOOD -> context.getString(R.string.condition_good)
            USUALLY -> context.getString(R.string.condition_usually)
            LIMITED_EDITION -> context.getString(R.string.condition_limited_edition)
            COLLECTIBLE -> context.getString(R.string.condition_collectible)
        }
    }

    fun conditionExplanation(context: Context): String {
        return when (this) {
            NEW -> context.getString(R.string.condition_info_new)
            BEST -> context.getString(R.string.condition_info_best)
            GOOD -> context.getString(R.string.condition_info_good)
            USUALLY -> context.getString(R.string.condition_info_usually)
            LIMITED_EDITION -> context.getString(R.string.condition_info_limited_edition)
            COLLECTIBLE -> context.getString(R.string.condition_info_collectible)
        }
    }
}