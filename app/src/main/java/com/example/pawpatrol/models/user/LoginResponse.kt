package com.example.pawpatrol.models.user

import com.example.pawpatrol.models.user.Users

data class LoginResponse(
    val access_token: String,
    val token_type: String,
    val user: Users
)