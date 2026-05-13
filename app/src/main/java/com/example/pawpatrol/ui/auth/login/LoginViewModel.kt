package com.example.pawpatrol.ui.auth.login
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.example.pawpatrol.repository.AuthRepository
import com.example.pawpatrol.models.user.LoginResponse

import kotlinx.coroutines.launch

class LoginViewModel(
    private val repository: AuthRepository
) : ViewModel() {

    private val _loginResult =
        MutableLiveData<LoginResponse>()

    val loginResult:
            LiveData<LoginResponse>
            = _loginResult

    private val _isLoading =
        MutableLiveData<Boolean>()

    val isLoading:
            LiveData<Boolean>
            = _isLoading

    private val _error =
        MutableLiveData<String>()

    val error:
            LiveData<String>
            = _error

    fun login(
        email: String,
        password: String
    ) {

        viewModelScope.launch {

            try {

                _isLoading.value = true

                val response =
                    repository.login(
                        email,
                        password
                    )

                if (response.isSuccessful &&
                    response.body() != null
                ) {

                    _loginResult.value =
                        response.body()

                } else {

                    _error.value =
                        "Login gagal"

                }

            } catch (e: Exception) {

                _error.value =
                    e.message

            } finally {

                _isLoading.value = false

            }
        }
    }
}