package com.example.pawpatrol.models.product

data class ProductCreateRequest(
    val nama_produk: String,
    val deskripsi: String? = null,
    val harga: Int,
    val stok: Int,
    val foto: String? = null
)