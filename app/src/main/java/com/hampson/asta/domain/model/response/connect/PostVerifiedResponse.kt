package com.hampson.asta.domain.model.response.connect

import com.hampson.asta.domain.model.response.Meta
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PostVerifiedResponse(
    val payload: Verified? = Verified(null, null, null),
    val meta: Meta? = Meta(null, null)
)

@JsonClass(generateAdapter = true)
data class Verified(
    val accessToken: String?,
    val refreshToken: String?,
    val userType: String?
)

