package com.example.pawpatrol.ui.animal

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.pawpatrol.api.ApiClient
import com.example.pawpatrol.databinding.ActivityDetailAnimalBinding
import com.example.pawpatrol.models.animal.Animals
import com.example.pawpatrol.models.order.CreateAnimalAdoptionRequest
import com.example.pawpatrol.models.order.CreateOrderRequest
import com.example.pawpatrol.session.SessionManager
import kotlinx.coroutines.launch
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols

class DetailAnimalActivity: AppCompatActivity (){
    private lateinit var binding: ActivityDetailAnimalBinding
    private lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailAnimalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val imgDetail = binding.imgDetailPet.toString()
        val txtDetailName = binding.txtDetailName
        val txtDetailPrice = binding.txtDetailPrice
        val txtDetailAge = binding.txtDetailAge
        val txtDetailDesc = binding.txtDetailDesc
        val animal = intent.getParcelableExtra("animal", Animals::class.java)

        sessionManager= SessionManager(this@DetailAnimalActivity)

        val symbols = DecimalFormatSymbols().apply {
            groupingSeparator = '.'
            decimalSeparator = ','
        }
        val decimalFormat= DecimalFormat("#,###", symbols)

        txtDetailName.text = animal?.nama_hewan
        txtDetailPrice.text = "Rp ${decimalFormat.format(animal?.harga ?:0)}"
        txtDetailAge.text=animal?.umur.toString()
        txtDetailDesc.text=animal?.deskripsi.toString()

        binding.btnBack.setOnClickListener {
            finish()
        }

        binding.btnEdit.setOnClickListener {
            val intent = Intent(
                this,
                EditAnimalActivity::class.java
            )

            intent.putExtra("animal_id",animal?.id)
            intent.putExtra("nama_hewan",txtDetailName.text)
            intent.putExtra("harga",animal?.harga ?:0)
            intent.putExtra("umur",txtDetailAge.text)
            intent.putExtra("deskripsi", txtDetailDesc.text)

            startActivity(intent)
        }

        binding.btnDelete.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("Hapus Hewan")
                .setMessage("Yakin ingin menghapus hewan ini?")
                .setPositiveButton("Ya") { _, _ ->

                    animal?.id?.let { id ->

                        deleteAnimal(id)
                        finish()
                    }
                }
                .setNegativeButton("Batal", null)
                .show()

        }

        binding.btnAdoptNow.setOnClickListener {
            val userId=sessionManager.getUserId()
            val harga=(animal?.harga ?:0).toInt()

            val request= CreateOrderRequest(
                total_harga = harga,
                user_id = userId
            )
            lifecycleScope.launch {

                try {
                    val response = ApiClient.getApiService(this@DetailAnimalActivity)
                        .createOrder(request)

                    if (response.isSuccessful) {
                        val orderResponse = response.body()
                        val orderId = orderResponse?.id ?: 0
                        val adoptionRequest = CreateAnimalAdoptionRequest(
                            biaya_adopsi = harga,
                            order_id = orderId,
                            animal_id = animal?.id ?:0
                        )

                        val adoptionResponse = ApiClient.getApiService(this@DetailAnimalActivity)
                            .createAnimalAdopt(adoptionRequest)

                        if (adoptionResponse.isSuccessful) {
                            Toast.makeText(
                                this@DetailAnimalActivity,
                                "Adopsi berhasil",
                                Toast.LENGTH_SHORT
                            ).show()
                        } else {
                            Toast.makeText(
                                this@DetailAnimalActivity,
                                "Gagal tambah animal adoption",
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                    } else {

                        Toast.makeText(
                            this@DetailAnimalActivity,
                            "Gagal order",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                } catch (e: Exception) {

                    Toast.makeText(
                        this@DetailAnimalActivity,
                        e.message,
                        Toast.LENGTH_SHORT
                    ).show()

                } finally {

                }
            }
        }
    }
    private fun deleteAnimal(id: Int) {

        lifecycleScope.launch {

            try {

                val response =
                    ApiClient.getApiService(this@DetailAnimalActivity)
                        .deleteAnimal(id)

                if (response.isSuccessful) {

                    Toast.makeText(
                        this@DetailAnimalActivity,
                        "Data berhasil dihapus",
                        Toast.LENGTH_SHORT
                    ).show()

                    finish()

                } else {

                    Toast.makeText(
                        this@DetailAnimalActivity,
                        "Gagal menghapus data",
                        Toast.LENGTH_SHORT
                    ).show()
                }

            } catch (e: Exception) {

                Toast.makeText(
                    this@DetailAnimalActivity,
                    e.message.toString(),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}