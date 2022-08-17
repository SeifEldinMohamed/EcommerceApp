package com.seif.ecommerceapp.data.remote

import com.seif.ecommerceapp.data.remote.models.*
import retrofit2.Response
import retrofit2.http.*

interface ProductsApi {

    @GET("product/v1/get")
    suspend fun fetchProducts(): Response<List<Product>>

    @POST("user/v1/registration")
    suspend fun createUser(@Body params: SignupRequest):Response<SignupResponse>

    @POST("user/v1/login")
    suspend fun loginUser(@Body params: LoginRequest): Response<LoginResponse>

}