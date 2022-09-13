package com.hampson.asta.domain.model.request.connect

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PostVerified(
    val token: String,
    val verifiedNumber: String
)