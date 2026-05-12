package com.example.pawpatrol.services

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    // =====================================================
    // USERS
    // =====================================================

    @GET("users")
    fun getUsers(): Call<List<User>>

    @GET("users/{id}")
    fun getUserById(
        @Path("id") id: Int
    ): Call<User>

    @POST("users")
    fun createUser(
        @Body user: User
    ): Call<User>

    @PUT("users/{id}")
    fun updateUser(
        @Path("id") id: Int,
        @Body user: User
    ): Call<User>

    @DELETE("users/{id}")
    fun deleteUser(
        @Path("id") id: Int
    ): Call<Map<String, String>>

    // =====================================================
    // KATEGORI HEWAN
    // =====================================================

    @GET("kategori-hewan")
    fun getKategoriHewan(): Call<List<KategoriHewan>>

    @GET("kategori-hewan/{id}")
    fun getKategoriById(
        @Path("id") id: Int
    ): Call<KategoriHewan>

    @POST("kategori-hewan")
    fun createKategori(
        @Body kategori: KategoriHewan
    ): Call<KategoriHewan>

    @PUT("kategori-hewan/{id}")
    fun updateKategori(
        @Path("id") id: Int,
        @Body kategori: KategoriHewan
    ): Call<KategoriHewan>

    @DELETE("kategori-hewan/{id}")
    fun deleteKategori(
        @Path("id") id: Int
    ): Call<Map<String, String>>

    // =====================================================
    // HEWAN
    // =====================================================

    @GET("hewan")
    fun getHewan(): Call<List<Hewan>>

    @GET("hewan/{id}")
    fun getHewanById(
        @Path("id") id: Int
    ): Call<Hewan>

    @GET("hewan")
    fun filterHewan(
        @Query("kategori_id") kategoriId: Int?,
        @Query("status") status: String?
    ): Call<List<Hewan>>

    @POST("hewan")
    fun createHewan(
        @Body hewan: Hewan
    ): Call<Hewan>

    @PUT("hewan/{id}")
    fun updateHewan(
        @Path("id") id: Int,
        @Body hewan: Hewan
    ): Call<Hewan>

    @DELETE("hewan/{id}")
    fun deleteHewan(
        @Path("id") id: Int
    ): Call<Map<String, String>>

    // =====================================================
    // PRODUK
    // =====================================================

    @GET("produk")
    fun getProduk(): Call<List<Produk>>

    @GET("produk/{id}")
    fun getProdukById(
        @Path("id") id: Int
    ): Call<Produk>

    @POST("produk")
    fun createProduk(
        @Body produk: Produk
    ): Call<Produk>

    @PUT("produk/{id}")
    fun updateProduk(
        @Path("id") id: Int,
        @Body produk: Produk
    ): Call<Produk>

    @DELETE("produk/{id}")
    fun deleteProduk(
        @Path("id") id: Int
    ): Call<Map<String, String>>

    // =====================================================
    // GROOMING
    // =====================================================

    @GET("grooming")
    fun getGrooming(): Call<List<Grooming>>

    @GET("grooming/{id}")
    fun getGroomingById(
        @Path("id") id: Int
    ): Call<Grooming>

    @POST("grooming")
    fun createGrooming(
        @Body grooming: Grooming
    ): Call<Grooming>

    @PUT("grooming/{id}")
    fun updateGrooming(
        @Path("id") id: Int,
        @Body grooming: Grooming
    ): Call<Grooming>

    @DELETE("grooming/{id}")
    fun deleteGrooming(
        @Path("id") id: Int
    ): Call<Map<String, String>>

    // =====================================================
    // TRANSAKSI
    // =====================================================

    @GET("transaksi")
    fun getTransaksi(): Call<List<Transaksi>>

    @GET("transaksi/{id}")
    fun getTransaksiById(
        @Path("id") id: Int
    ): Call<Transaksi>

    @GET("transaksi")
    fun filterTransaksi(
        @Query("user_id") userId: Int?,
        @Query("status_pembayaran") status: String?,
        @Query("jenis_transaksi") jenis: String?
    ): Call<List<Transaksi>>

    @POST("transaksi")
    fun createTransaksi(
        @Body transaksi: Transaksi
    ): Call<Transaksi>

    @PATCH("transaksi/{id}/status")
    fun updateStatusTransaksi(
        @Path("id") id: Int,
        @Query("status_pembayaran") status: String
    ): Call<Transaksi>

    @DELETE("transaksi/{id}")
    fun deleteTransaksi(
        @Path("id") id: Int
    ): Call<Map<String, String>>
}