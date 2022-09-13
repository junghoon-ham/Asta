package com.hampson.asta.domain.use_case.connect

import com.hampson.asta.domain.model.response.connect.PostVerifiedResponse
import com.hampson.asta.domain.repository.ConnectRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

class PostVerified(private val repository: ConnectRepository) {

    suspend operator fun invoke(
        token: String,
        verifiedNumber: String
    ): Flow<PostVerifiedResponse> {
        return repository.postVerified(token, verifiedNumber).flowOn(Dispatchers.Default)
    }
}