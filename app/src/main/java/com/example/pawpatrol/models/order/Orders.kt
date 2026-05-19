package com.example.pawpatrol.models.order

import android.os.Parcelable
import com.example.pawpatrol.models.user.Users
import kotlinx.parcelize.Parcelize

@Parcelize
data class Orders(
    val id: Int,
    val total_harga: Int,
    val status: String,
    val created_at: String? = null,
    val user_id: Int,
    val user: Users? = null
): Parcelable