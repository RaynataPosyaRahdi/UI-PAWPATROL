package com.example.pawpatrol.ui.product

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.pawpatrol.api.ApiClient

import com.example.pawpatrol.databinding.ActivityDetailProductBinding
import com.example.pawpatrol.models.order.CreateAnimalAdoptionRequest
import com.example.pawpatrol.models.order.CreateOrderRequest
import com.example.pawpatrol.models.product.ProductCreateRequest
import com.example.pawpatrol.models.product.ProductOrderCreateRequest
import com.example.pawpatrol.models.product.Products
import com.example.pawpatrol.session.SessionManager
import com.example.pawpatrol.ui.animal.EditAnimalActivity
import kotlinx.coroutines.launch
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols

class DetailProductActivity : AppCompatActivity(){
    private lateinit var binding: ActivityDetailProductBinding
    private lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailProductBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val imgDetail = binding.imgDetailProduct.toString()
        val txtDetailName = binding.txtDetailName
        val txtDetailPrice = binding.txtDetailPrice
        val txtDetailStock = binding.txtDetailStock
        val txtDetailDesc = binding.txtDetailDesc

        val product = intent.getParcelableExtra("product", Products::class.java)
        val sessionManager = SessionManager(this@DetailProductActivity)

        val symbols = DecimalFormatSymbols().apply {
            groupingSeparator = '.'
            decimalSeparator = ','
        }
        val decimalFormat= DecimalFormat("#,###", symbols)

        txtDetailName.text = product?.nama_produk
        txtDetailPrice.text = "Rp ${decimalFormat.format(product?.harga ?:0)}"
        txtDetailDesc.text=product?.deskripsi.toString()
        txtDetailStock.text=product?.stok.toString()

        binding.btnBack.setOnClickListener {
            finish()
        }

        binding.btnEdit.setOnClickListener {
            val intent=Intent(
                this,
                EditProductActivity::class.java
            )

            intent.putExtra("produk_id",product?.id)
            intent.putExtra("nama_produk",product?.nama_produk)
            intent.putExtra("stok",product?.stok ?:0)
            intent.putExtra("harga",product?.harga ?:0)
            intent.putExtra("deskripsi",product?.deskripsi)

            startActivity(intent)
        }

        binding.btnDelete.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("Hapus produk")
                .setMessage("Yakin ingin menghapus produk ini?")
                .setPositiveButton("Ya") { _, _ ->

                    product?.id?.let { id ->

                        deleteProduct(id)
                        finish()
                    }
                }
                .setNegativeButton("Batal", null)
                .show()

        }

        binding.btnCart.setOnClickListener {

        }

        binding.btnCheckout.setOnClickListener {

            val editText = EditText(this).apply {
                hint = "Masukkan jumlah produk"
                inputType = InputType.TYPE_CLASS_NUMBER
            }

            AlertDialog.Builder(this)
                .setTitle("Jumlah Produk")
                .setView(editText)
                .setPositiveButton("Checkout") { _, _ ->

                    val quantityText = editText.text.toString()

                    if (quantityText.isEmpty()) {

                        Toast.makeText(
                            this,
                            "Jumlah produk harus diisi",
                            Toast.LENGTH_SHORT
                        ).show()

                        return@setPositiveButton
                    }

                    val quantity = quantityText.toInt()

                    val userId = sessionManager.getUserId()
                    val harga = product?.harga ?: 0

                    val totalHarga = harga * quantity

                    val request = CreateOrderRequest(
                        total_harga = totalHarga,
                        user_id = userId
                    )

                    lifecycleScope.launch {

                        try {

                            val response = ApiClient
                                .getApiService(this@DetailProductActivity)
                                .createOrder(request)

                            if (response.isSuccessful) {

                                val orderResponse = response.body()
                                val orderId = orderResponse?.id ?: 0

                                val productRequest = ProductOrderCreateRequest(
                                    quantity = quantity,
                                    harga = totalHarga,
                                    order_id = orderId,
                                    product_id = product?.id ?: 0
                                )

                                val productResponse = ApiClient
                                    .getApiService(this@DetailProductActivity)
                                    .createOrderProduct(productRequest)

                                if (productResponse.isSuccessful) {

                                    Toast.makeText(
                                        this@DetailProductActivity,
                                        "Pembelian produk berhasil",
                                        Toast.LENGTH_SHORT
                                    ).show()

                                } else {

                                    Toast.makeText(
                                        this@DetailProductActivity,
                                        "Pembelian produk gagal",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }

                            } else {

                                Toast.makeText(
                                    this@DetailProductActivity,
                                    "Gagal order",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }

                        } catch (e: Exception) {

                            Toast.makeText(
                                this@DetailProductActivity,
                                e.message,
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }

                }
                .setNegativeButton("Batal", null)
                .show()
        }
    }
    private fun deleteProduct(id: Int) {

        lifecycleScope.launch {

            try {

                val response =
                    ApiClient.getApiService(this@DetailProductActivity)
                        .deleteProduct(id)

                if (response.isSuccessful) {

                    Toast.makeText(
                        this@DetailProductActivity,
                        "Data berhasil dihapus",
                        Toast.LENGTH_SHORT
                    ).show()

                    finish()

                } else {

                    Toast.makeText(
                        this@DetailProductActivity,
                        "Gagal menghapus data",
                        Toast.LENGTH_SHORT
                    ).show()
                }

            } catch (e: Exception) {

                Toast.makeText(
                    this@DetailProductActivity,
                    e.message.toString(),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

}