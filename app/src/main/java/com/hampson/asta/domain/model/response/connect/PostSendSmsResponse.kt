package com.hampson.asta.domain.model.response.connect

import com.hampson.asta.domain.model.response.Meta
import com.hampson.asta.domain.model.response.Payload
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PostSendSmsResponse(
    val payload: Payload? = Payload(null),
    val meta: Meta? = Meta(null, null)
)

@JsonClass(generateAdapter = true)
data class SmsAuth(
    val token: String?
)