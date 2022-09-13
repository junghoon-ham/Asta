package com.hampson.asta.domain.model.request.connect

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PostSendSmsRequest(
    val receiver: String
)