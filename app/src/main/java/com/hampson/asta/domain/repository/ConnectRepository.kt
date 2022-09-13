package com.hampson.asta.domain.repository

import com.hampson.asta.domain.model.response.connect.PostSendSmsResponse
import com.hampson.asta.domain.model.response.connect.PostVerifiedResponse
import kotlinx.coroutines.flow.Flow

interface ConnectRepository {

    suspend fun postSendSms(
        phoneNumber: String,
    ): Flow<PostSendSmsResponse>

    suspend fun postVerified(
        token: String,
        verifiedNumber: String
    ): Flow<PostVerifiedResponse>

    // DataStore
    suspend fun saveUser(isSignIn: Boolean)

    suspend fun getUserSignIn(): Flow<Boolean>
}