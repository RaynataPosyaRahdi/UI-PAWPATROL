package com.example.pawpatrol.ui.product

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.pawpatrol.R
import com.example.pawpatrol.models.animal.Animals
import com.example.pawpatrol.models.product.Products
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols

class ProductAdapter(
    private val onItemClick: (Products) -> Unit,
) : ListAdapter<Products, ProductAdapter.ProductViewHolder>(ProductCallBack) {

    class ProductViewHolder(
        itemView: View,
        val onClick: (Products) -> Unit
    ) : RecyclerView.ViewHolder(itemView) {

        private val id: TextView = itemView.findViewById(R.id.txtId)

        private val image: ImageView = itemView.findViewById(R.id.imgProduct)

        private val nama: TextView = itemView.findViewById(R.id.txtName)

        private val harga: TextView = itemView.findViewById(R.id.txtPrice)

        private val desc: TextView = itemView.findViewById(R.id.txtDesc)

        private var currentProduct: Products? = null

        init {

            itemView.setOnClickListener {

                currentProduct?.let {

                    onClick(it)
                }
            }
        }

        fun bind(products: Products) {
            val symbols = DecimalFormatSymbols().apply {
                groupingSeparator = '.'
                decimalSeparator = ','
            }
            val decimalFormat= DecimalFormat("#,###", symbols)

            currentProduct = products

            id.text = products.id.toString()

            nama.text = products.nama_produk

            harga.text = "Rp ${decimalFormat.format(products.harga)}"

            desc.text = products.deskripsi

            /*
            nanti untuk image API

            Glide.with(itemView.context)
                .load(products.foto)
                .into(image)
             */
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_product, parent, false)

        return ProductViewHolder(view, onItemClick)
    }

    override fun onBindViewHolder(
        holder: ProductViewHolder,
        position: Int
    ) {

        val product = getItem(position)

        holder.bind(product)
    }
}

object ProductCallBack : DiffUtil.ItemCallback<Products>() {

    override fun areItemsTheSame(
        oldItem: Products,
        newItem: Products
    ): Boolean {

        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: Products,
        newItem: Products
    ): Boolean {

        return oldItem == newItem
    }
}