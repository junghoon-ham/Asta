package com.hampson.asta.domain.use_case.connect

import com.hampson.asta.domain.model.response.connect.PostSendSmsResponse
import com.hampson.asta.domain.repository.ConnectRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

class SaveUser(private val repository: ConnectRepository) {

    suspend operator fun invoke(isSignIn: Boolean) {
        repository.saveUser(isSignIn)
    }
}