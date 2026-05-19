package com.example.pawpatrol.ui.animal

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import java.util.Locale
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.pawpatrol.R
import com.example.pawpatrol.models.animal.Animals
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols


class AnimalAdapter (
    private val onItemClick: (Animals) -> Unit,
    private val onCheckoutClick: (Animals) -> Unit) :
    ListAdapter<Animals, AnimalAdapter.AnimalViewHolder>(AnimalCallBack) {

    class AnimalViewHolder(
        itemView: View,
        val onItemClick: (Animals) -> Unit,
        val onCheckoutClick: (Animals) -> Unit
    ):
        RecyclerView.ViewHolder(itemView){
            private val id: TextView = itemView.findViewById(R.id.txtId)
            private val image: ImageView = itemView.findViewById(R.id.imgPet)
            private val nama: TextView = itemView.findViewById(R.id.txtName)
            private val harga: TextView = itemView.findViewById(R.id.txtPrice)
            private val umur: TextView = itemView.findViewById(R.id.txtAge)
            private val desc: TextView = itemView.findViewById(R.id.txtDesc)
            private val btnadopt: Button = itemView.findViewById(R.id.btnAdopt)

            private var currentAnimal: Animals? = null

            init {

                itemView.setOnClickListener {

                    currentAnimal?.let {

                        onItemClick(it)
                    }
                }

                btnadopt.setOnClickListener {

                    currentAnimal?.let {

                        onCheckoutClick(it)
                    }
                }
            }

        fun bind(animals: Animals){
            currentAnimal=animals

            val symbols = DecimalFormatSymbols().apply {
                groupingSeparator = '.'
                decimalSeparator = ','
            }
            val decimalFormat= DecimalFormat("#,###", symbols)
            id.text= animals.id.toString()
            harga.text="Rp ${decimalFormat.format(animals.harga)}"
            nama.text=animals.nama_hewan
            desc.text=animals.deskripsi
            umur.text=animals.umur.toString()

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimalViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_animal, parent, false)
        return AnimalViewHolder(
            view,
            onItemClick,
            onCheckoutClick
        )
    }

    override fun onBindViewHolder(holder: AnimalViewHolder, position: Int) {
        val animals = getItem(position)
        holder.bind(animals)
    }
}
object AnimalCallBack : DiffUtil.ItemCallback<Animals>() {
    override fun areItemsTheSame(oldItem: Animals, newItem: Animals): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Animals, newItem: Animals): Boolean {
        return oldItem == newItem
    }
}