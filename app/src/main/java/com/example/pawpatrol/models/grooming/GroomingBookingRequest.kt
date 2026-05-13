package com.example.pawpatrol.models.grooming
data class GroomingBookingRequest(
    val booking_date: String,
    val catatan: String? = null,
    val order_id: Int,
    val user_id: Int,
    val grooming_service_id: Int
)