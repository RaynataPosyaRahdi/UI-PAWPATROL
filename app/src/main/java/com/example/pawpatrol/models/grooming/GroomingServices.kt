package com.example.pawpatrol.models.grooming

data class GroomingServices(
    val id: Int,
    val nama_layanan: String,
    val harga: Int,
    val deskripsi: String? = null
)