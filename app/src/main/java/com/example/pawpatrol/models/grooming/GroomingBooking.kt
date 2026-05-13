package com.example.pawpatrol.models.grooming

import com.example.pawpatrol.models.order.Orders
import com.example.pawpatrol.models.user.Users

data class GroomingBooking(

    val id: Int,

    val booking_date: String,

    val catatan: String? = null,

    val status: String,

    val order_id: Int,

    val user_id: Int,

    val grooming_service_id: Int,

    val order: Orders? = null,

    val user: Users? = null,

    val grooming_service: GroomingServices? = null
)