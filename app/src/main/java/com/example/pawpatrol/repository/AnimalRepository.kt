package com.example.pawpatrol.repository

import com.example.pawpatrol.api.ApiService
import com.example.pawpatrol.models.animal.AnimalCreateRequest

class AnimalRepository(
    private val apiService: ApiService
) {

    suspend fun getAnimals() =
        apiService.getAnimals()

    suspend fun getAnimalById(
        id: Int
    ) = apiService.getAnimalById(id)

    suspend fun createAnimal(
        request: AnimalCreateRequest
    ) = apiService.createAnimal(
        request
    )
}