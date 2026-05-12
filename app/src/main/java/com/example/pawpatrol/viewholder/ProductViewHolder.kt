package com.example.pawpatrol.viewholder

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pawpatrol.R

class ProductViewHolder(itemView: View)
    : RecyclerView.ViewHolder(itemView) {

    val imgPet: ImageView = itemView.findViewById(R.id.imgPet)
    val txtId: TextView = itemView.findViewById(R.id.txtId)
    val txtName: TextView = itemView.findViewById(R.id.txtName)
    val txtType: TextView = itemView.findViewById(R.id.txtType)
    val txtDesc: TextView = itemView.findViewById(R.id.txtDesc)
    val txtPrice: TextView = itemView.findViewById(R.id.txtPrice)
    val ratingPet: RatingBar = itemView.findViewById(R.id.ratingPet)
    val btnAdopt: Button = itemView.findViewById(R.id.btnAdopt)
}