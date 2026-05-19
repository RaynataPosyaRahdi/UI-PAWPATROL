package com.example.pawpatrol.ui.product

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.pawpatrol.api.ApiClient
import com.example.pawpatrol.databinding.ActivityAddAnimalBinding
import com.example.pawpatrol.databinding.ActivityAddProductBinding
import com.example.pawpatrol.models.animal.AnimalCreateRequest
import com.example.pawpatrol.models.product.ProductCreateRequest
import kotlinx.coroutines.launch

class EditProductActivity: AppCompatActivity() {
    private lateinit var binding: ActivityAddProductBinding

    private var productId = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddProductBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.txtJudul.text="Edit Produk"
        binding.txtDescJudul.text="Edit produk PawPatrol"

        productId = intent.getIntExtra("produk_id", 0)
        setupEditData()

        binding.btnBack.setOnClickListener {
            finish()
        }

        binding.btnSimpan.setOnClickListener {
            updateProduct()
        }
    }

    private fun setupEditData() {
        binding.etNamaProduk.setText(
            intent.getStringExtra("nama_produk")
        )

        binding.etStok.setText(
            intent.getIntExtra("stok", 0).toString()
        )

        binding.etHarga.setText(
            intent.getIntExtra("harga", 0).toString()
        )

        binding.etDeskripsi.setText(
            intent.getStringExtra("deskripsi")
        )
    }
    private fun updateProduct() {

        val nama = binding.etNamaProduk.text.toString().trim()

        val stokText = binding.etStok.text.toString().trim()

        val hargaText = binding.etHarga.text.toString().trim()

        val deskripsi = binding.etDeskripsi.text.toString().trim()

        // VALIDATION

        if (nama.isEmpty()) {
            binding.etNamaProduk.error = "Nama produk wajib diisi"
            return
        }

        if (stokText.isEmpty()) {
            binding.etStok.error = "stok wajib diisi"
            return
        }

        if (hargaText.isEmpty()) {
            binding.etHarga.error = "Harga wajib diisi"
            return
        }

        val stok = stokText.toInt()

        val harga = hargaText.toInt()


        val request = ProductCreateRequest(
            nama_produk = nama,
            stok = stok,
            harga = harga,
            deskripsi = deskripsi,
            foto = null,
        )

        lifecycleScope.launch {

            try {

                binding.btnSimpan.isEnabled = false

                val response = ApiClient
                    .getApiService(this@EditProductActivity)
                    .updateProduct(
                        productId,
                        request
                    )

                if (response.isSuccessful) {

                    Toast.makeText(
                        this@EditProductActivity,
                        "Produk berhasil diupdate",
                        Toast.LENGTH_SHORT
                    ).show()

                    finish()

                } else {

                    Toast.makeText(
                        this@EditProductActivity,
                        "Update gagal",
                        Toast.LENGTH_SHORT
                    ).show()
                }

            } catch (e: Exception) {

                Toast.makeText(
                    this@EditProductActivity,
                    e.message,
                    Toast.LENGTH_SHORT
                ).show()

            } finally {

                binding.btnSimpan.isEnabled = true
            }
        }
    }
}