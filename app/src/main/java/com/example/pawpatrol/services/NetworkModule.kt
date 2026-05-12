package com.example.pawpatrol.services

object NetworkModule {

    fun provideApiService(): ApiService {
        return RetrofitClient.apiService
    }
}