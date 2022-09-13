package com.hampson.asta.presentation.connect.certification

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hampson.asta.domain.model.response.connect.PostSendSmsResponse
import com.hampson.asta.domain.model.response.connect.PostVerifiedResponse
import com.hampson.asta.domain.use_case.connect.ConnectUseCases
import com.hampson.asta.util.Constants.REG_PHONE_NUMBER
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.regex.Pattern
import javax.inject.Inject

@HiltViewModel
class CertificationViewModel @Inject constructor(
    private val connectUseCases: ConnectUseCases
) : ViewModel() {

    private val _smsResult = MutableStateFlow(PostSendSmsResponse())
    val smsResult: StateFlow<PostSendSmsResponse> get() = _smsResult.asStateFlow()

    private val _verifiedResult = MutableStateFlow(PostVerifiedResponse())
    val verifiedResult: StateFlow<PostVerifiedResponse> get() = _verifiedResult.asStateFlow()

    private val _token = MutableStateFlow("")
    val token: StateFlow<String> get() = _token.asStateFlow()

    fun isValidationPhoneNumber(phoneNumber: String): Boolean {
        return Pattern.matches(REG_PHONE_NUMBER, phoneNumber)
    }

    fun sendPhoneNumber(
        phoneNumber: String
    ) {
        viewModelScope.launch {
            connectUseCases.postSendSms(phoneNumber).collectLatest {
                _smsResult.value = it
                if (!it.payload?.smsAuth?.token.isNullOrEmpty())
                    _token.value = it.payload?.smsAuth?.token!!
            }
        }
    }

    fun sendVerifiedNumber(
        token: String,
        verifiedNumber: String
    ) {
        viewModelScope.launch {
            connectUseCases.postVerified(token, verifiedNumber).collectLatest {
                _verifiedResult.value = PostVerifiedResponse()
                _verifiedResult.value = it
            }
        }
    }

    // DataStore
    fun saveUserSignIn(value: Boolean) = viewModelScope.launch(Dispatchers.IO) {
        connectUseCases.saveUser(value)
    }

    suspend fun getUserSignIn() = withContext(Dispatchers.IO) {
        connectUseCases.getUserSignIn().first()
    }
}