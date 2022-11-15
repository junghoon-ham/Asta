package com.hampson.asta.presentation.register_auction

import android.net.Uri
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

    val isWritingRegister = MutableStateFlow(false)

    val images = MutableStateFlow<List<Uri>>(listOf())
    val title = MutableStateFlow<String?>(null)
    val information = MutableStateFlow<String?>(null)
    val category = MutableStateFlow<CategoryType?>(null)
    val condition = MutableStateFlow<ConditionType?>(null)
    val trade = MutableStateFlow<TradeType?>(null)
    val priceStart = MutableStateFlow<Int?>(null)
    val priceHope = MutableStateFlow<Int?>(null)
    val priceIncrease = MutableStateFlow<Int?>(null)
    val days = MutableStateFlow<Int?>(null)
    val hour = MutableStateFlow<Int?>(null)
    val minute = MutableStateFlow<Int?>(null)

    fun resetRegisterData() {
        images.value = listOf()
        title.value = null
        information.value = null
        category.value = null
        condition.value = null
        trade.value = null
        priceStart.value = null
        priceHope.value = null
        priceIncrease.value = null
        days.value = null
        hour.value = null
        minute.value = null

        isWritingRegister.value = false
    }
}