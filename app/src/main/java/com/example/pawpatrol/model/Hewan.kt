package com.example.pawpatrol.model

enum class StatusHewan {
    TERSEDIA,
    DIADOPSI
}

data class Hewan(
    val id: Int,
    val namaHewan: String,
    val umur: Int,
    val foto: String?,
    val status: StatusHewan,

    // Foreign Key
    val idKategori: Int,
    val idUser: Int?
)