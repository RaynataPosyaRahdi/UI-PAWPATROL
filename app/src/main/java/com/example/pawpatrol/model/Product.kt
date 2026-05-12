package com.example.pawpatrol.model

data class Product (
    val id: Int,
    val name: String,
    val type: String,
    val price: Double,
    val rating: Float,
    val imageResId: Int,
    val description: String
)