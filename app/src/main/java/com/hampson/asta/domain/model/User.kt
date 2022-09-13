package com.hampson.asta.domain.model

data class User(
    val id: Long = 0L,
    val name: String = "",
    val profileImg: String = "",
    val phoneNumber: String = "",
    val email: String = ""
)