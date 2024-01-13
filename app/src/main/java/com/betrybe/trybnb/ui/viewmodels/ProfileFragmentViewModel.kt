package com.betrybe.trybnb.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.betrybe.trybnb.common.ApiIdlingResource
import com.betrybe.trybnb.data.api.BookerServiceClient
import com.betrybe.trybnb.data.models.AuthLoginData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProfileFragmentViewModel : ViewModel() {

    private val bookerService = BookerServiceClient.instance

    private val _authTextProfile = MutableLiveData("")
    val authTextProfile: LiveData<String>
        get() = _authTextProfile

    private suspend fun authLogin(username: String, password: String): AuthLoginData? {
        val responseAuthLogin = bookerService.authLogin(username, password)
        return responseAuthLogin.body()
    }

    fun authLoginBooker(username: String, password: String) {
        viewModelScope.launch {
            ApiIdlingResource.increment()
            val tokenAuthLogin = withContext(Dispatchers.IO) {
                authLogin(username, password)
            }
            val token = tokenAuthLogin?.token
            val authMessage = if (token != null) {
                "Login feito com sucesso!"
            } else {
                "Insira um Login e Senha válidas!"
            }
            _authTextProfile.postValue(authMessage)
            ApiIdlingResource.decrement()
        }
    }
}
