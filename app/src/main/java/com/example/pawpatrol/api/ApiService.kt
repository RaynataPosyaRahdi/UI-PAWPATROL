package com.example.pawpatrol.api

import com.example.pawpatrol.models.animal.Animals
import com.example.pawpatrol.models.animal.AnimalCreateRequest
import com.example.pawpatrol.models.order.CreateAnimalAdoptionRequest
import com.example.pawpatrol.models.order.CreateOrderRequest
import com.example.pawpatrol.models.order.Orders
import com.example.pawpatrol.models.product.Products
import com.example.pawpatrol.models.product.ProductCreateRequest
import com.example.pawpatrol.models.product.ProductOrderCreateRequest
import com.example.pawpatrol.models.user.LoginRequest
import com.example.pawpatrol.models.user.LoginResponse
import com.example.pawpatrol.models.user.Users
import com.example.pawpatrol.models.user.UserCreateRequest

import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    // =================================================
    // AUTH
    // =================================================

    @POST("auth/register")
    suspend fun register(
        @Body request: UserCreateRequest
    ): Response<Users>

    @POST("auth/login")
    suspend fun login(
        @Body request: LoginRequest
    ): Response<LoginResponse>


    // =================================================
    // USERS
    // =================================================

    @GET("users")
    suspend fun getUsers():
            Response<List<Users>>


    // =================================================
    // ANIMALS
    // =================================================

    @GET("animals")
    suspend fun getAnimals(
        @Query("status") status: String
    ): Response<List<Animals>>

    @GET("animals/{id}")
    suspend fun getAnimalById(
        @Path("id") id: Int
    ): Response<Animals>

    @POST("animals")
    suspend fun createAnimal(
        @Body request: AnimalCreateRequest
    ): Response<Animals>

    @PUT("animals/{animal_id}")
    suspend fun updateAnimal(
        @Path("animal_id") animalId: Int,
        @Body request: AnimalCreateRequest
    ): Response<Animals>

    @DELETE("animals/{id}")
    suspend fun deleteAnimal(
        @Path("id") id: Int
    ): Response<Unit>

    // =================================================
    // PRODUCTS
    // =================================================

    @GET("products")
    suspend fun getProducts():
            Response<List<Products>>

    @GET("products/{id}")
    suspend fun getProductById(
        @Path("id") id: Int
    ): Response<Products>

    @POST("products")
    suspend fun createProduct(
        @Body request: ProductCreateRequest
    ): Response<Products>

    @PUT("products/{product_id}")
    suspend fun updateProduct(
        @Path("product_id") productId: Int,
        @Body request: ProductCreateRequest
    ): Response<Products>

    @DELETE("products/{id}")
    suspend fun deleteProduct(
        @Path("id") id: Int
    ): Response<Unit>


    // =================================================
    // CARTS
    // =================================================


    // =================================================
    // ORDERS
    // =================================================

    @POST("orders")
    suspend fun createOrder(
        @Body request: CreateOrderRequest
    ): Response<Orders>

    @POST("animal-adoptions")
    suspend fun createAnimalAdopt(
        @Body request: CreateAnimalAdoptionRequest
    ): Response<CreateAnimalAdoptionRequest>

    @POST("order-products")
    suspend fun createOrderProduct(
        @Body request: ProductOrderCreateRequest
    ): Response<ProductOrderCreateRequest>
}