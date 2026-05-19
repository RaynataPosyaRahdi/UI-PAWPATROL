package com.example.pawpatrol.ui.product

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.pawpatrol.api.ApiClient
import com.example.pawpatrol.databinding.ActivityAddProductBinding
import com.example.pawpatrol.models.product.ProductCreateRequest
import kotlinx.coroutines.launch

class AddProductActivity: AppCompatActivity() {
    private lateinit var binding: ActivityAddProductBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddProductBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener {
            finish()
        }

        binding.btnSimpan.setOnClickListener {
            createProduct()
        }

        binding.btnUploadFoto.setOnClickListener {

        }
    }

    private fun createProduct(){
        val nama = binding.etNamaProduk.text.toString().trim()
        val hargaText = binding.etHarga.text.toString().trim()
        val stokText = binding.etStok.text.toString().trim()
        val desc = binding.etDeskripsi.text.toString().trim()

        if (nama.isEmpty()){
            binding.etNamaProduk.error="Nama produk wajib diisi"
            return
        }

        if (hargaText.isEmpty()){
            binding.etHarga.error = "Harga produk wajib diisi"
            return
        }

        if (stokText.isEmpty()){
            binding.etStok.error = "Stok produk wajib diisi"
            return
        }

        if (desc.isEmpty()){
            binding.etDeskripsi.error = "Deskripsi produk wajib diisi"
        }

        val harga = hargaText.toInt()
        val stok = stokText.toInt()

        val request = ProductCreateRequest(
            nama_produk = nama,
            harga = harga,
            deskripsi = desc,
            stok = stok,
            foto = null
        )

        lifecycleScope.launch {

            try {

                binding.btnSimpan.isEnabled = false

                val response = ApiClient.getApiService(this@AddProductActivity)
                    .createProduct(request)

                if (response.isSuccessful) {

                    Toast.makeText(
                        this@AddProductActivity,
                        "Produk berhasil ditambahkan",
                        Toast.LENGTH_SHORT
                    ).show()

                    finish()

                } else {

                    Toast.makeText(
                        this@AddProductActivity,
                        "Gagal menambahkan produk",
                        Toast.LENGTH_SHORT
                    ).show()
                }

            } catch (e: Exception) {

                Toast.makeText(
                    this@AddProductActivity,
                    e.message,
                    Toast.LENGTH_SHORT
                ).show()

            } finally {

                binding.btnSimpan.isEnabled = true
            }
        }
    }
}