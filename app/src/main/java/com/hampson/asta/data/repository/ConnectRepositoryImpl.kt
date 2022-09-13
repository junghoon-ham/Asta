package com.hampson.asta.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import com.hampson.asta.data.api.ConnectApi
import com.hampson.asta.data.repository.ConnectRepositoryImpl.PreferencesKeys.IS_SIGN_IN
import com.hampson.asta.domain.model.request.connect.PostSendSmsRequest
import com.hampson.asta.domain.model.request.connect.PostVerified
import com.hampson.asta.domain.model.response.connect.PostSendSmsResponse
import com.hampson.asta.domain.model.response.connect.PostVerifiedResponse
import com.hampson.asta.domain.repository.ConnectRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ConnectRepositoryImpl @Inject constructor(
    private val api: ConnectApi,
    private val dataStore: DataStore<Preferences>
) : ConnectRepository {

    override suspend fun postSendSms(
        phoneNumber: String
    ): Flow<PostSendSmsResponse> {
        return flow {
            emit(api.postSendSms(PostSendSmsRequest(phoneNumber)))
        }
    }

    override suspend fun postVerified(
        token: String,
        verifiedNumber: String
    ): Flow<PostVerifiedResponse> {
        return flow {
            emit(api.postVerified(PostVerified(token, verifiedNumber)))
        }
    }

    // DataStore
    private object PreferencesKeys {
        val IS_SIGN_IN = booleanPreferencesKey("is_sign_in")
    }

    override suspend fun saveUser(isSignIn: Boolean) {
        dataStore.edit { prefs ->
            prefs[IS_SIGN_IN] = isSignIn
        }
    }

    override suspend fun getUserSignIn(): Flow<Boolean> {
        return dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    exception.printStackTrace()
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map { prefs ->
                prefs[IS_SIGN_IN] ?: false
            }
    }
}