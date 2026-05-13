package com.example.pawpatrol.repository

import com.example.pawpatrol.api.ApiService
import com.example.pawpatrol.models.grooming.GroomingBookingRequest

class GroomingRepository(
    private val apiService: ApiService
) {

    suspend fun getServices() =
        apiService.getGroomingServices()

    suspend fun createBooking(
        request: GroomingBookingRequest
    ) = apiService.createBooking(
        request
    )
}