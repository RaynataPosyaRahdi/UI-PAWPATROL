package com.example.pawpatrol.models.animal

import com.example.pawpatrol.models.category.Categories
import com.example.pawpatrol.models.user.Users

data class Animals(
    val id: Int,
    val nama_hewan: String,
    val umur: Int,
    val deskripsi: String? = null,
    val foto: String? = null,
    val status_adopsi: String,
    val created_at: String? = null,
    val kategori_id: Int,
    val adopter_id: Int? = null,
    val category: Categories? = null,
    val adopter: Users? = null
)