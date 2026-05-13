package com.example.pawpatrol.models

data class Transaksi(
    val id: Int,
    val idUser: Int,
    val idProduk: Int?,
    val idGrooming: Int?,
    val tanggal: String,
    val total: Int,
    val statusPembayaran: String
)