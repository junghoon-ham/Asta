package com.hampson.asta.domain.util

enum class ConditionType(
    val conditionName: String
) {
    NEW("새상품"),
    BEST("상태 최상"),
    GOOD("상태 양호"),
    USUALLY("상태 보통"),
    LIMITED_EDITION("리미티드 에디션"),
    COLLECTIBLE("소장가치");

    fun conditionExplanation(): String {
        return when (this) {
            NEW -> "미사용 및 미개봉인 상태"
            BEST -> "사용은 하였지만 새 제품과 다름없는 상태"
            GOOD -> "사용감이 미세하게 있는 상태"
            USUALLY -> "사용감이 있는 상태"
            LIMITED_EDITION -> "한정판 또는 구할 수 없는 제품"
            COLLECTIBLE -> "희소하거나 소장가치가 있는 제품"
        }
    }
}