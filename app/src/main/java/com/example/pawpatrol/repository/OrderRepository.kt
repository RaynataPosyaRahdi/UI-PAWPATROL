package com.example.pawpatrol.repository

import com.example.pawpatrol.api.ApiService

class OrderRepository(
    private val apiService: ApiService
) {

    suspend fun getOrders() =
        apiService.getOrders()
}
