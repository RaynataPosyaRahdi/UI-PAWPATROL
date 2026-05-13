package com.example.pawpatrol.ui.main

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.pawpatrol.api.ApiClient
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        testApi()
    }

    private fun testApi() {

        lifecycleScope.launch {

            try {

                val response =
                    ApiClient.apiService.testApi()

                if (response.isSuccessful) {

                    Log.d(
                        "API_TEST",
                        response.body().toString()
                    )

                } else {

                    Log.e(
                        "API_TEST",
                        "Failed"
                    )
                }

            } catch (e: Exception) {

                Log.e(
                    "API_TEST",
                    e.message.toString()
                )
            }
        }
    }
}