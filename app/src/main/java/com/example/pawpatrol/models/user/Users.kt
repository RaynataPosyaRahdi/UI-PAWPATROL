package com.example.pawpatrol.models.user

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Users(
    val id: Int,
    val nama: String,
    val email: String,
    val alamat: String? = null,
    val created_at: String? = null
): Parcelable