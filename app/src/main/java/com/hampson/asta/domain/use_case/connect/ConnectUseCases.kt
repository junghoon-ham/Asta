package com.hampson.asta.domain.use_case.connect

data class ConnectUseCases(
    val postSendSms: PostSendSms,
    val postVerified: PostVerified,
    val saveUser: SaveUser,
    val getUserSignIn: GetUserSignIn
)