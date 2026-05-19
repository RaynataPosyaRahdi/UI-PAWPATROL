package com.example.pawpatrol.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.pawpatrol.databinding.FragmentPetAdoptBinding
import com.example.pawpatrol.api.ApiClient
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.pawpatrol.models.order.CreateAnimalAdoptionRequest
import com.example.pawpatrol.models.order.CreateOrderRequest
import com.example.pawpatrol.session.SessionManager
import com.example.pawpatrol.ui.animal.AnimalAdapter
import com.example.pawpatrol.ui.animal.DetailAnimalActivity
import kotlinx.coroutines.launch
import kotlin.jvm.java

class PetAdoptFragment : Fragment() {

    private var _binding: FragmentPetAdoptBinding? = null
    private val binding get() = _binding!!
    private lateinit var animalAdapter: AnimalAdapter

    private lateinit var sessionManager: SessionManager

    private val addAnimalLauncher =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {

            if (it.resultCode == AppCompatActivity.RESULT_OK) {

                getAnimals()
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        sessionManager= SessionManager(requireContext())

        _binding = FragmentPetAdoptBinding.inflate(inflater, container, false)

        setupRecyclerView()

        getAnimals()

        return binding.root
    }

    override fun onResume() {
        super.onResume()

        getAnimals()
    }

    private fun setupRecyclerView() {
        val userId=sessionManager.getUserId()
        animalAdapter = AnimalAdapter(

            onItemClick = { animal ->

                val intent = Intent(
                    requireContext(),
                    DetailAnimalActivity::class.java
                )

                intent.putExtra("animal", animal)

                startActivity(intent)
            },

            onCheckoutClick = { animal ->
                val harga=animal.harga.toInt()

                val request= CreateOrderRequest(
                    total_harga = harga,
                    user_id = userId
                )
                lifecycleScope.launch {

                    try {
                        val response = ApiClient.getApiService(requireContext())
                            .createOrder(request)

                        if (response.isSuccessful) {
                            val orderResponse = response.body()
                            val orderId = orderResponse?.id ?:0
                            val adoptionRequest = CreateAnimalAdoptionRequest(
                                biaya_adopsi = harga,
                                order_id = orderId,
                                animal_id = animal.id
                            )

                            val adoptionResponse = ApiClient.getApiService(requireContext())
                                .createAnimalAdopt(adoptionRequest)

                            if (adoptionResponse.isSuccessful){
                                Toast.makeText(
                                    requireContext(),
                                    "Adopsi berhasil",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }else{
                                Toast.makeText(
                                    requireContext(),
                                    "Gagal tambah animal adoption",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }

                        } else {

                            Toast.makeText(
                                requireContext(),
                                "Gagal order",
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                    } catch (e: Exception) {

                        Toast.makeText(
                            requireContext(),
                            e.message,
                            Toast.LENGTH_SHORT
                        ).show()

                    } finally {

                    }
                }
            }
        )

        binding.recyclerPetAdopt.apply {

            layoutManager = GridLayoutManager(requireContext(), 2)

            adapter = animalAdapter
        }
    }

    private fun getAnimals() {

        viewLifecycleOwner.lifecycleScope.launch {

            try {

                val response = ApiClient.getApiService(requireContext())
                    .getAnimals("tersedia")

                if (response.isSuccessful) {

                    val animalList = response.body() ?: emptyList()

                    animalAdapter.submitList(animalList)

                } else {

                    Toast.makeText(
                        requireContext(),
                        "Failed Load Data",
                        Toast.LENGTH_SHORT
                    ).show()
                }

            } catch (e: Exception) {

                Log.d("API_ERROR", e.message.toString())

                Toast.makeText(
                    requireContext(),
                    e.message,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}