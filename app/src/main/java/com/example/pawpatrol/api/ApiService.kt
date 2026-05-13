package com.example.pawpatrol.api

import com.example.pawpatrol.models.animal.Animals
import com.example.pawpatrol.models.animal.AnimalCreateRequest
import com.example.pawpatrol.models.grooming.GroomingBooking
import com.example.pawpatrol.models.grooming.GroomingBookingRequest
import com.example.pawpatrol.models.grooming.GroomingServices
import com.example.pawpatrol.models.order.Orders
import com.example.pawpatrol.models.product.Products
import com.example.pawpatrol.models.product.ProductCreateRequest
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
    suspend fun getAnimals():
            Response<List<Animals>>

    @GET("animals/{id}")
    suspend fun getAnimalById(
        @Path("id") id: Int
    ): Response<Animals>

    @POST("animals")
    suspend fun createAnimal(
        @Body request: AnimalCreateRequest
    ): Response<Animals>


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


    // =================================================
    // GROOMING SERVICES
    // =================================================

    @GET("grooming-services")
    suspend fun getGroomingServices():
            Response<List<GroomingServices>>


    // =================================================
    // GROOMING BOOKINGS
    // =================================================

    @POST("grooming-bookings")
    suspend fun createBooking(
        @Body request: GroomingBookingRequest
    ): Response<GroomingBooking>


    // =================================================
    // ORDERS
    // =================================================

    @GET("orders")
    suspend fun getOrders():
            Response<List<Orders>>

}