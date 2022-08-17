package com.seif.ecommerceapp.data.remote

import com.seif.ecommerceapp.data.remote.models.*
import retrofit2.Response
import retrofit2.http.*

interface ProductsApi {
    // https://online-shoppingg.herokuapp.com/user/v1/login
    // https://online-shoppingg.herokuapp.com/product/v1/get
    @GET("product/v1/get")
    suspend fun fetchProducts(): Response<List<Product>>

    @POST("user/v1/registration")
    // @Headers("accept: */*" , "Content-Type: application/json")
    suspend fun createUser(@Body params: SignupRequest):Response<SignupResponse>

    @POST("user/v1/login")
    suspend fun loginUser(@Body params: LoginRequest): Response<LoginResponse>

    @PUT("user/v1/put/{}") // to update recorded data
    suspend fun updateUser(@Path("user_id") email: String, @Body params: User): Response<User>
}