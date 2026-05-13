package com.example.pawpatrol.api

import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("/")
    suspend fun testApi(): Response<Map<String, String>>
}