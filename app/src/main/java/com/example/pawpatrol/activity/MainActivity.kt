package com.example.pawpatrol.activity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pawpatrol.R
import com.example.pawpatrol.adapter.ProductAdapter
import com.example.pawpatrol.model.Product
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ProductAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = GridLayoutManager(this, 2)
        recyclerView.itemAnimator = DefaultItemAnimator()

        val fabTambah: FloatingActionButton = findViewById(R.id.fabCart)

        val listProduct = mutableListOf(
            // --- KUCING ---
            Product(1, "Milo", "Kucing Persia", "Kucing lucu dan aktif", 450000.0, 4.5f, R.drawable.kucing_persia),
            Product(2, "Oyen", "Kucing Anggora", "Kucing penurut dan suka bermain", 450000.0, 4.0f, R.drawable.kucing_anggora),
            Product(3, "Luna", "Kucing Siam", "Kucing anggun dengan corak unik", 550000.0, 4.7f, R.drawable.kucing_siam),
            Product(4, "Kiko", "Kucing Scottish Fold", "Telinga lipat yang unik", 900000.0, 4.8f, R.drawable.kucing_scottish_fold),
            Product(5, "Bella", "Kucing Maine Coon", "Ras terbesar yang lembut", 1500000.0, 5.0f, R.drawable.kucing_maine_coon),

            // --- ANJING ---
            Product(6, "Bobi", "Anjing Husky", "Anjing gagah dan setia", 750000.0, 5.0f, R.drawable.anjing_husky),
            Product(7, "Rex", "Anjing Golden", "Sangat ramah dan setia", 850000.0, 4.9f, R.drawable.anjing_golden),
            Product(8, "Bruno", "Anjing Bulldog", "Tenang dan tangguh", 1200000.0, 4.5f, R.drawable.anjing_bulldog),
            Product(9, "Molly", "Anjing Poodle", "Pintar dengan bulu cantik", 650000.0, 4.6f, R.drawable.anjing_poodle),
            Product(10, "Zorro", "Anjing Beagle", "Lincah dan berani", 700000.0, 4.4f, R.drawable.anjing_beagle)
        )

        adapter = ProductAdapter(listProduct)
        recyclerView.adapter = adapter

        fabTambah.setOnClickListener {

            val newList = listProduct.toMutableList()

            newList.add(
                Product(
                    newList.size + 1,
                    "Produk Baru",
                    "Kucing Baru",
                    "Produk tambahan otomatis",
                    300000.0,
                    4.0f,
                    R.drawable.ic_launcher_foreground
                )
            )

            adapter.submitList(newList)

            listProduct.clear()
            listProduct.addAll(newList)

            Toast.makeText(
                this,
                "Hewan berhasil ditambahkan 🐾",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}