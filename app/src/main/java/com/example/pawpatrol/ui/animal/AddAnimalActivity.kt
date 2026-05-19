package com.example.pawpatrol.ui.animal

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.pawpatrol.databinding.ActivityAddAnimalBinding
import com.example.pawpatrol.models.animal.AnimalCreateRequest
import com.example.pawpatrol.api.ApiClient
import kotlinx.coroutines.launch

class AddAnimalActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddAnimalBinding
    private val categoryList = listOf(
        "Kucing",
        "Anjing",
        "Kelinci",
        "Burung"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddAnimalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupDropdown()

        setupAction()
    }

    private fun setupDropdown() {

        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_dropdown_item_1line,
            categoryList
        )

        binding.dropdownCategory.setAdapter(adapter)
    }

    private fun setupAction() {

        binding.btnBack.setOnClickListener {
            finish()
        }

        binding.btnSimpan.setOnClickListener {
            createAnimal()
        }
    }

    private fun createAnimal() {

        val nama = binding.etNamaHewan.text.toString().trim()

        val umurText = binding.etUmur.text.toString().trim()

        val hargaText = binding.etHarga.text.toString().trim()

        val deskripsi = binding.etDeskripsi.text.toString().trim()

        val kategori = binding.dropdownCategory.text.toString().trim()

        // VALIDATION

        if (nama.isEmpty()) {
            binding.etNamaHewan.error = "Nama hewan wajib diisi"
            return
        }

        if (umurText.isEmpty()) {
            binding.etUmur.error = "Umur wajib diisi"
            return
        }

        if (hargaText.isEmpty()) {
            binding.etHarga.error = "Harga wajib diisi"
            return
        }

        if (kategori.isEmpty()) {
            binding.dropdownCategory.error = "Kategori wajib dipilih"
            return
        }

        val umur = umurText.toInt()

        val harga = hargaText.toInt()

        // sementara mapping hardcode
        val kategoriId = when (kategori) {
            "Kucing" -> 1
            "Anjing" -> 2
            "Kelinci" -> 3
            "Burung" -> 4
            else -> 1
        }

        val request = AnimalCreateRequest(
            nama_hewan = nama,
            umur = umur,
            harga = harga,
            deskripsi = deskripsi,
            foto = null,
            kategori_id = kategoriId
        )

        lifecycleScope.launch {

            try {

                binding.btnSimpan.isEnabled = false

                val response = ApiClient.getApiService(this@AddAnimalActivity)
                    .createAnimal(request)

                if (response.isSuccessful) {

                    Toast.makeText(
                        this@AddAnimalActivity,
                        "Hewan berhasil ditambahkan",
                        Toast.LENGTH_SHORT
                    ).show()

                    finish()

                } else {

                    Toast.makeText(
                        this@AddAnimalActivity,
                        "Gagal menambahkan hewan",
                        Toast.LENGTH_SHORT
                    ).show()
                }

            } catch (e: Exception) {

                Toast.makeText(
                    this@AddAnimalActivity,
                    e.message,
                    Toast.LENGTH_SHORT
                ).show()

            } finally {

                binding.btnSimpan.isEnabled = true
            }
        }
    }
}