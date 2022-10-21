package com.hampson.asta.presentation.register_auction

import androidx.lifecycle.ViewModel
import com.hampson.asta.domain.util.CategoryType
import com.hampson.asta.domain.util.ConditionType
import com.hampson.asta.domain.util.TradeType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class RegisterAuctionViewModel @Inject constructor(

) : ViewModel() {

    val category = MutableStateFlow<CategoryType?>(null)
    val condition = MutableStateFlow<ConditionType?>(null)
    val trade = MutableStateFlow<TradeType?>(null)
    val priceStart = MutableStateFlow<Int?>(null)
    val priceHope = MutableStateFlow<Int?>(null)
    val priceIncrease = MutableStateFlow<Int?>(null)
    val days = MutableStateFlow<Int?>(null)
    val hour = MutableStateFlow<Int?>(null)
    val minute = MutableStateFlow<Int?>(null)
}