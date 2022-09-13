package com.hampson.asta.domain.use_case.connect

import com.hampson.asta.domain.repository.ConnectRepository
import kotlinx.coroutines.flow.Flow

class GetUserSignIn(private val repository: ConnectRepository) {

    suspend operator fun invoke(): Flow<Boolean> {
        return repository.getUserSignIn()
    }
}