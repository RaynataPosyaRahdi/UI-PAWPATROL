package com.example.pawpatrol.ui

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
import com.example.pawpatrol.api.ApiClient
import com.example.pawpatrol.databinding.FragmentProductBinding
import com.example.pawpatrol.ui.product.DetailProductActivity
import com.example.pawpatrol.ui.product.ProductAdapter
import kotlinx.coroutines.launch

class ProductFragment : Fragment() {

    private var _binding: FragmentProductBinding? = null
    private val binding get() = _binding!!

    private lateinit var productAdapter: ProductAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentProductBinding.inflate(inflater, container, false)

        setupRecyclerView()

        getProducts()

        return binding.root
    }

    override fun onResume() {
        super.onResume()

        getProducts()
    }

    private fun setupRecyclerView() {

        productAdapter = ProductAdapter(

                onItemClick = { product ->

                    val intent = Intent(
                        requireContext(),
                        DetailProductActivity::class.java
                    )

                    intent.putExtra("product", product)

                    startActivity(intent)
                },
            )

        binding.recyclerProduct.apply {

            layoutManager = GridLayoutManager(requireContext(), 2)

            adapter = productAdapter
        }
    }

    private fun getProducts() {

        viewLifecycleOwner.lifecycleScope.launch {

            try {

                val response = ApiClient.getApiService(requireContext()).getProducts()

                if (response.isSuccessful) {

                    val productList = response.body() ?: emptyList()

                    productAdapter.submitList(productList)

                } else {

                    Toast.makeText(
                        requireContext(),
                        "Failed Load Products",
                        Toast.LENGTH_SHORT
                    ).show()
                }

            } catch (e: Exception) {

                Log.d("PRODUCT_ERROR", e.message.toString())

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