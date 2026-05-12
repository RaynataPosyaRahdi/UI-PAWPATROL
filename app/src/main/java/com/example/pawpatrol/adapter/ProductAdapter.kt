package com.example.pawpatrol.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.pawpatrol.R
import com.example.pawpatrol.model.Product
import com.example.pawpatrol.viewholder.ProductViewHolder
import com.example.pawpatrol.activity.DetailProductActivity
import com.example.pawpatrol.activity.CheckoutActivity
import com.example.pawpatrol.utils.ProductDiffUtil

class ProductAdapter(
    private val listProduct: MutableList<Product>
) : RecyclerView.Adapter<ProductViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_product, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = listProduct[position]

        holder.txtId.text = product.id.toString()
        holder.txtName.text = product.name
        holder.txtType.text = product.type
        holder.txtDesc.text = product.description
        holder.txtPrice.text = product.price.toString()
        holder.ratingPet.rating = product.rating
        holder.imgPet.setImageResource(product.imageResId)

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, DetailProductActivity::class.java)
            intent.putExtra("PRODUCT_NAME", product.name)
            intent.putExtra("PRODUCT_TYPE", product.type)
            intent.putExtra("PRODUCT_PRICE", product.price)
            intent.putExtra("PRODUCT_DESC", product.description)
            intent.putExtra("PRODUCT_IMAGE", product.imageResId)
            holder.itemView.context.startActivity(intent)
        }

        holder.btnAdopt.setOnClickListener {
            val intent = Intent(holder.itemView.context, CheckoutActivity::class.java)
            intent.putExtra("PRODUCT_PRICE", product.price)
            holder.itemView.context.startActivity(intent)
        }

        holder.itemView.setOnLongClickListener {
            val newList = listProduct.toMutableList()
            newList.removeAt(position)
            submitList(newList)

            Toast.makeText(
                holder.itemView.context,
                "Hewan berhasil dihapus 🐾",
                Toast.LENGTH_SHORT
            ).show()

            true
        }
    }

    fun submitList(newList: MutableList<Product>) {
        val diff = ProductDiffUtil(listProduct, newList)
        val result = DiffUtil.calculateDiff(diff)

        listProduct.clear()
        listProduct.addAll(newList)

        result.dispatchUpdatesTo(this)
    }

    override fun getItemCount(): Int = listProduct.size
}