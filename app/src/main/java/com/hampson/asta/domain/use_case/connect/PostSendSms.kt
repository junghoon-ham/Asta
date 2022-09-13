package com.hampson.asta.domain.use_case.connect

import com.hampson.asta.domain.model.response.connect.PostSendSmsResponse
import com.hampson.asta.domain.repository.ConnectRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

class PostSendSms(private val repository: ConnectRepository) {

    suspend operator fun invoke(phoneNumber: String): Flow<PostSendSmsResponse> {
        return repository.postSendSms(phoneNumber).flowOn(Dispatchers.Default)
    }
}