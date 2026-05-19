package com.example.pawpatrol.ui.animal

import android.R
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.pawpatrol.api.ApiClient
import com.example.pawpatrol.databinding.ActivityAddAnimalBinding
import com.example.pawpatrol.models.animal.AnimalCreateRequest
import com.example.pawpatrol.models.animal.Animals
import kotlinx.coroutines.launch

class EditAnimalActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddAnimalBinding

    private var animalId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddAnimalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // AMBIL ID DI AWAL
        animalId = intent.getIntExtra("animal_id", 0)

        // SETUP UI
        binding.txtJudul.text = "Edit Hewan"
        binding.txtDescJudul.text = "Edit hewan PawPatrol"
        binding.btnSimpan.text = "Update Hewan"

        setupEditData()

        setupCategoryDropdown()

        binding.btnBack.setOnClickListener {
            finish()
        }

        binding.btnSimpan.setOnClickListener {
            updateAnimal()
        }
    }

    private fun setupEditData() {

        binding.etNamaHewan.setText(
            intent.getStringExtra("nama_hewan")
        )

        binding.etUmur.setText(
            intent.getStringExtra("umur")
        )

        binding.etHarga.setText(
            intent.getIntExtra("harga", 0).toString()
        )

        binding.etDeskripsi.setText(
            intent.getStringExtra("deskripsi")
        )

        // OPTIONAL CATEGORY
        val kategori = intent.getStringExtra("kategori")

        binding.dropdownCategory.setText(
            kategori,
            false
        )
    }

    private fun setupCategoryDropdown() {

        val categories = listOf(
            "Kucing",
            "Anjing",
            "Kelinci",
            "Burung"
        )

        val adapter = ArrayAdapter(
            this,
            R.layout.simple_dropdown_item_1line,
            categories
        )

        binding.dropdownCategory.setAdapter(adapter)
    }

    private fun updateAnimal() {

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

                val response = ApiClient
                    .getApiService(this@EditAnimalActivity)
                    .updateAnimal(
                        animalId,
                        request
                    )

                if (response.isSuccessful) {

                    Toast.makeText(
                        this@EditAnimalActivity,
                        "Hewan berhasil diupdate",
                        Toast.LENGTH_SHORT
                    ).show()

                    finish()

                } else {

                    Toast.makeText(
                        this@EditAnimalActivity,
                        "Update gagal",
                        Toast.LENGTH_SHORT
                    ).show()
                }

            } catch (e: Exception) {

                Toast.makeText(
                    this@EditAnimalActivity,
                    e.message,
                    Toast.LENGTH_SHORT
                ).show()

            } finally {

                binding.btnSimpan.isEnabled = true
            }
        }
    }
}