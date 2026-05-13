package com.example.pawpatrol.models.user


data class UserCreateRequest(
    val nama: String,
    val email: String,
    val password: String,
    val alamat: String? = null
)