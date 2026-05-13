package com.example.pawpatrol.models.order

import com.example.pawpatrol.models.product.Products

data class OrderProduct(
    val id: Int,
    val quantity: Int,
    val harga: Int,
    val order_id: Int,
    val product_id: Int,
    val product: Products? = null
)