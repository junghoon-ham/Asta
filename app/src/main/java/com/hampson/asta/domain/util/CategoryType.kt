package com.hampson.asta.domain.util

import android.content.Context
import com.hampson.asta.R

enum class CategoryType(
    private val categoryNameId: Int
) {
    DIGITAL(R.string.digital),
    HOME_APPLIANCES(R.string.home_appliances),
    FURNITURE(R.string.furniture),
    CAR(R.string.car),
    HOBBY(R.string.hobby),
    SPORTS(R.string.sports),
    FASHION(R.string.fashion),
    FOOD(R.string.food),
    SCARCITY(R.string.scarcity);

    fun categoryName(context: Context?): String? =
        context?.getString(categoryNameId)

    fun categoryImage(): Int {
        return when (this) {
            DIGITAL -> R.drawable.ic_baseline_computer_40
            HOME_APPLIANCES -> R.drawable.ic_baseline_wb_incandescent_40
            FURNITURE -> R.drawable.ic_baseline_table_restaurant_40
            CAR -> R.drawable.ic_baseline_directions_car_40
            HOBBY -> R.drawable.ic_baseline_surfing_40
            SPORTS -> R.drawable.ic_baseline_sports_basketball_40
            FASHION -> R.drawable.ic_baseline_woman_40
            FOOD -> R.drawable.ic_baseline_fastfood_40
            SCARCITY -> R.drawable.ic_baseline_diamond_40
        }
    }
}