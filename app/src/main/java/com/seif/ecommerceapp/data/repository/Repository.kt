package com.seif.ecommerceapp.data.repository

import com.seif.ecommerceapp.data.remote.models.*
import com.seif.ecommerceapp.utils.NetworkResult

interface Repository {
    suspend fun createUser(user: SignupRequest): NetworkResult<SignupResponse>?
    suspend fun loginUser(user: LoginRequest): NetworkResult<LoginResponse>?
    suspend fun fetchProducts(): NetworkResult<List<Product>>?

}