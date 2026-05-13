package com.example.pawpatrol.models.product


data class Products(
    val id: Int,
    val nama_produk: String,
    val deskripsi: String? = null,
    val harga: Int,
    val stok: Int,
    val foto: String? = null,
    val created_at: String? = null
)