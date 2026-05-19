package com.example.pawpatrol.models.product

data class ProductOrderCreateRequest (
    val quantity: Int,
    val harga: Int,
    val order_id: Int,
    val product_id: Int

)