package com.example.pawpatrol.models.category

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Categories(
    val id: Int,
    val nama_kategori: String
): Parcelable