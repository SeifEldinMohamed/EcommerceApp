package com.seif.ecommerceapp.data.repository

import com.seif.ecommerceapp.data.remote.models.Product
import com.seif.ecommerceapp.data.remote.models.SignupRequest
import com.seif.ecommerceapp.data.remote.models.SignupResponse
import com.seif.ecommerceapp.utils.NetworkResult

interface Repository {
    suspend fun createUser(user: SignupRequest): NetworkResult<SignupResponse>?
    suspend fun fetchProducts(): NetworkResult<List<Product>>?
}