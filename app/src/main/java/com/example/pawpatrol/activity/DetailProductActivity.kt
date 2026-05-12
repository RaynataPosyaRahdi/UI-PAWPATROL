package com.example.pawpatrol.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.pawpatrol.R

class DetailProductActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val btnAdopt: Button = findViewById(R.id.btnCheckout)

        val name = intent.getStringExtra("PRODUCT_NAME")
        val type = intent.getStringExtra("PRODUCT_TYPE")
        val price = intent.getDoubleExtra("PRODUCT_PRICE", 0.0)
        val description = intent.getStringExtra("PRODUCT_DESC")
        val image = intent.getIntExtra("PRODUCT_IMAGE", 0)

        val imgProduct: ImageView = findViewById(R.id.detailImage)
        val txtName: TextView = findViewById(R.id.detailName)
        val txtType: TextView = findViewById(R.id.detailType)
        val txtPrice: TextView = findViewById(R.id.detailPrice)
        val txtDesc: TextView = findViewById(R.id.detailDesc)

        imgProduct.setImageResource(image)
        txtName.text = name
        txtType.text = type
        txtPrice.text = "Rp $price"
        txtDesc.text = description

        btnAdopt.setOnClickListener {
            val intent = Intent(this, CheckoutActivity::class.java)
            intent.putExtra("PRODUCT_PRICE", price)
            startActivity(intent)
        }
    }
}