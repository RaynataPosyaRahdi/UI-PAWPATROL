package com.example.pawpatrol.api

import android.content.Context
import com.example.pawpatrol.utils.Constant
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    fun getApiService(
        context: Context
        ): ApiService{

    val loggingInterceptor =
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    val client = OkHttpClient.Builder()
        .addInterceptor(
            AuthInterceptor(context)
        )
        .addInterceptor(loggingInterceptor)
        .build()

    val retrofit = Retrofit.Builder()
        .baseUrl(Constant.BASE_URL)
        .client(client)
        .addConverterFactory(
            GsonConverterFactory.create()
        )
        .build()

    return retrofit.create(ApiService::class.java)
    }
}