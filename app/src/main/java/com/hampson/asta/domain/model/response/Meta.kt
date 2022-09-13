package com.hampson.asta.domain.model.response

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Meta(
    val message: String?,
    val status: Boolean?
)