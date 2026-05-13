package com.example.pawpatrol.api

import android.content.Context
import com.example.pawpatrol.utils.Constant
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    fun create(
        context: Context
    ): ApiService {

        val client = OkHttpClient.Builder()
            .addInterceptor(
                AuthInterceptor(context)
            )
            .build()

        return Retrofit.Builder()
            .baseUrl(Constant.BASE_URL)
            .client(client)
            .addConverterFactory(
                GsonConverterFactory.create()
            )
            .build()
            .create(ApiService::class.java)
    }
}