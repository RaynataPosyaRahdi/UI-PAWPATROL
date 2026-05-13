package com.example.pawpatrol.models.animal


data class AnimalCreateRequest(
    val nama_hewan: String,
    val umur: Int,
    val deskripsi: String? = null,
    val foto: String? = null,
    val kategori_id: Int
)