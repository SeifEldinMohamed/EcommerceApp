package com.seif.ecommerceapp.data.remote

import android.util.Log
import com.seif.ecommerceapp.data.remote.models.*
import com.seif.ecommerceapp.utils.NetworkResult
import retrofit2.Response
import retrofit2.http.Body
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val productsApi: ProductsApi
) {

    suspend fun createUser(user: SignupRequest): NetworkResult<SignupResponse>? {
        val response: Response<SignupResponse> = productsApi.createUser(user)
        return handleCreateUserResponse(response)
    }

    private fun handleCreateUserResponse(response: Response<SignupResponse>): NetworkResult<SignupResponse>? {
        return when {
            response.message().toString().contains("timeout") -> NetworkResult.Error("Timeout")
            response.code() == 404 -> NetworkResult.Error("Not Found")

            response.isSuccessful -> { // we will return SignupResponse from api
                response.body()?.let {
                    NetworkResult.Success(it)
                }
            }
            else -> NetworkResult.Error(response.message() + response.code())
        }
    }

    suspend fun fetchProducts(): NetworkResult<List<Product>>? {
        val response: Response<List<Product>> = productsApi.fetchProducts()
        return handleProductResponse(response)
    }

    private fun handleProductResponse(response: Response<List<Product>>): NetworkResult<List<Product>>? {
        return when {
            response.message().toString().contains("timeout") -> NetworkResult.Error("Timeout")
            response.code() == 404 -> NetworkResult.Error("Not Found")
            response.body()
                .isNullOrEmpty() -> NetworkResult.Error("products Not Found.")

            response.isSuccessful -> { // we will return trending repositories from api
                response.body()?.let {
                    NetworkResult.Success(it)
                }
            }
            else -> {
                Log.d("register", "response Message: ${response.message()}")
                Log.d("register", "response code: ${response.code()}")
                Log.d("register", "response error body: ${response.errorBody()}")
                Log.d("register", "response body: ${response.body()}")
                Log.d("register", "response header: ${response.headers()}")
                NetworkResult.Error(response.message() + response.code() + response.errorBody() + response.body())
            }
        }
    }

    suspend fun loginUser(user: LoginRequest): NetworkResult<LoginResponse>? {
        val response: Response<LoginResponse> = productsApi.loginUser(user)
        return handleLoginUserResponse(response)
    }

    private fun handleLoginUserResponse(response: Response<LoginResponse>): NetworkResult<LoginResponse>? {
        return when {
            response.message().toString().contains("timeout") -> NetworkResult.Error("Timeout")
            response.code() == 404 -> NetworkResult.Error("Not Found")

            response.isSuccessful -> { // we will return trending repositories from api
                response.body()?.let {
                    NetworkResult.Success(it)
                }
            }
            else -> {
                NetworkResult.Error(response.message() + response.code())
            }
        }
    }
}