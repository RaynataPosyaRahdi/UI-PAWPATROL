package com.example.pawpatrol.models.product

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.io.Serializable

@Parcelize
data class Products(
    val id: Int,
    val nama_produk: String,
    val deskripsi: String? = null,
    val harga: Int,
    val stok: Int,
    val foto: String? = null,
) : Parcelable