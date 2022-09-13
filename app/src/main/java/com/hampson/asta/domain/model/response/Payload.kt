package com.hampson.asta.domain.model.response

import com.hampson.asta.domain.model.response.connect.SmsAuth
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Payload(
    val smsAuth: SmsAuth?
)

