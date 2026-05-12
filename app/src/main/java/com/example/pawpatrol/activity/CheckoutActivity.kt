package com.example.pawpatrol.activity

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.pawpatrol.R

class CheckoutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.checkout)

        val btnConfirm: Button = findViewById(R.id.btnConfirm)
        val txtPrice: TextView = findViewById(R.id.txtTotal)

        val price = intent.getDoubleExtra("PRODUCT_PRICE", 0.0)

        txtPrice.text = "Rp $price"

        btnConfirm.setOnClickListener {
            Toast.makeText(
                this,
                "Hewan berhasil diadopsi 🐾",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}