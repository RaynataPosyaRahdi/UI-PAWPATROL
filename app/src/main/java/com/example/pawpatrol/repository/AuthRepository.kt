package com.example.pawpatrol.repository
import com.example.pawpatrol.api.ApiService
import com.example.pawpatrol.models.user.LoginRequest
import com.example.pawpatrol.models.user.UserCreateRequest

class AuthRepository(
    private val apiService: ApiService
) {

    suspend fun login(
        email: String,
        password: String
    ) = apiService.login(
        LoginRequest(
            email,
            password
        )
    )

    suspend fun register(
        request: UserCreateRequest
    ) = apiService.register(
        request
    )
}