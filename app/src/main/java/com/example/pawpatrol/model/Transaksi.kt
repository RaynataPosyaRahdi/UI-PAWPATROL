package com.example.pawpatrol.model

enum class StatusPembayaran {
    PENDING,
    LUNAS,
    GAGAL
}

data class Transaksi(
    val id: Int,

    // Foreign Key
    val idUser: Int,
    val idProduk: Int?,
    val idGrooming: Int?,

    val tanggal: String,
    val total: Int,
    val statusPembayaran: StatusPembayaran
)