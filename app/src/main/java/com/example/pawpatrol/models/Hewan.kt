package com.example.pawpatrol.models

data class Hewan(
    val id: Int,
    val namaHewan: String,
    val umur: Int,
    val foto: String?,
    val status: String,
    val idKategori: Int,
    val idUser: Int?
)