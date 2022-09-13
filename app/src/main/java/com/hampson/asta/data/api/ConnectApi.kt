package com.hampson.asta.data.api

import com.hampson.asta.domain.model.request.connect.PostSendSmsRequest
import com.hampson.asta.domain.model.request.connect.PostVerified
import com.hampson.asta.domain.model.response.connect.PostSendSmsResponse
import com.hampson.asta.domain.model.response.connect.PostVerifiedResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface ConnectApi {
    @POST("api/v3/sms_auth/send_sms")
    suspend fun postSendSms(
        @Body postSendSmsRequest: PostSendSmsRequest
    ): PostSendSmsResponse

    @POST("api/v3/sms_auth/verified")
    suspend fun postVerified(
        @Body postVerified: PostVerified
    ): PostVerifiedResponse
}