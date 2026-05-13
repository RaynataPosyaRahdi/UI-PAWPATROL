package com.example.pawpatrol.ui.auth.register
import androidx.lifecycle.*
import com.example.pawpatrol.repository.AuthRepository
import com.example.pawpatrol.models.user.Users
import com.example.pawpatrol.models.user.UserCreateRequest

import kotlinx.coroutines.launch

class RegisterViewModel(
    private val repository: AuthRepository
) : ViewModel() {

    private val _registerResult =
        MutableLiveData<Users>()

    val registerResult:
            LiveData<Users>
            = _registerResult

    private val _error =
        MutableLiveData<String>()

    val error:
            LiveData<String>
            = _error

    fun register(
        request: UserCreateRequest
    ) {

        viewModelScope.launch {

            try {

                val response =
                    repository.register(
                        request
                    )

                if (response.isSuccessful &&
                    response.body() != null
                ) {

                    _registerResult.value =
                        response.body()

                } else {

                    _error.value =
                        "Register gagal"

                }

            } catch (e: Exception) {

                _error.value =
                    e.message

            }
        }
    }
}