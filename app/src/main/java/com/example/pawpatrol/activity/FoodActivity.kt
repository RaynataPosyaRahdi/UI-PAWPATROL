package com.example.pawpatrol.activity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pawpatrol.R
import com.example.pawpatrol.adapter.ProductAdapter
import com.example.pawpatrol.model.Produk
import com.example.pawpatrol.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FoodActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food)

        recyclerView =
            findViewById(R.id.recyclerView)

        recyclerView.layoutManager =
            GridLayoutManager(this, 2)

        getProduk()
    }

    private fun getProduk() {

        RetrofitClient.instance
            .getProduk()
            .enqueue(object :
                Callback<List<Produk>> {

                override fun onResponse(
                    call: Call<List<Produk>>,
                    response: Response<List<Produk>>
                ) {

                    if (response.isSuccessful) {

                        val produkList =
                            response.body()

                        recyclerView.adapter =
                            produkList?.let {
                                ProductAdapter(it)
                            }
                    }
                }

                override fun onFailure(
                    call: Call<List<Produk>>,
                    t: Throwable
                ) {

                    Toast.makeText(
                        this@FoodActivity,
                        t.message,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })
    }
}