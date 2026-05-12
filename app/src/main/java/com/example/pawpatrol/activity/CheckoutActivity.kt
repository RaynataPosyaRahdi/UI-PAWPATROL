package com.example.pawpatrol.activity

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.pawpatrol.R

class CheckoutActivity : AppCompatActivity() {

    private lateinit var etName: EditText
    private lateinit var etPhone: EditText
    private lateinit var etAddress: EditText
    private lateinit var etQuantity: EditText

    private lateinit var tvProductName: TextView
    private lateinit var tvProductPrice: TextView
    private lateinit var tvTotal: TextView

    private lateinit var btnConfirm: Button

    // =====================================
    // DATA PRODUK
    // =====================================

    private var productName = "Dog Food"
    private var productPrice = 50000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout)

        // =====================================
        // INIT VIEW
        // =====================================

        etName = findViewById(R.id.etName)
        etPhone = findViewById(R.id.etPhone)
        etAddress = findViewById(R.id.etAddress)
        etQuantity = findViewById(R.id.etQuantity)

        tvProductName = findViewById(R.id.tvProductName)
        tvProductPrice = findViewById(R.id.tvProductPrice)
        tvTotal = findViewById(R.id.tvTotal)

        btnConfirm = findViewById(R.id.btnConfirm)

        // =====================================
        // AMBIL DATA DARI INTENT
        // =====================================

        productName = intent.getStringExtra("product_name") ?: "Produk"

        productPrice = intent.getIntExtra(
            "product_price",
            0
        )

        // =====================================
        // TAMPILKAN DATA
        // =====================================

        tvProductName.text = "Produk: $productName"

        tvProductPrice.text = "Harga: Rp $productPrice"

        // =====================================
        // BUTTON CHECKOUT
        // =====================================

        btnConfirm.setOnClickListener {

            val qtyText = etQuantity.text.toString()

            if (qtyText.isEmpty()) {

                Toast.makeText(
                    this,
                    "Masukkan jumlah beli",
                    Toast.LENGTH_SHORT
                ).show()

                return@setOnClickListener
            }

            val qty = qtyText.toInt()

            val total = qty * productPrice

            tvTotal.text = "Total : Rp $total"

            Toast.makeText(
                this,
                "Checkout berhasil",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}
