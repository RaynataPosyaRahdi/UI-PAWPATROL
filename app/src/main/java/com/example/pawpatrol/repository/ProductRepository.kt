package com.example.pawpatrol.repository

import com.example.pawpatrol.api.ApiService
import com.example.pawpatrol.models.product.ProductCreateRequest

class ProductRepository(
    private val apiService: ApiService
) {

    suspend fun getProducts() =
        apiService.getProducts()

    suspend fun getProductById(
        id: Int
    ) = apiService.getProductById(id)

    suspend fun createProduct(
        request: ProductCreateRequest
    ) = apiService.createProduct(
        request
    )
}