package com.seif.ecommerceapp.data.repository

import com.seif.ecommerceapp.data.local.entities.OrderEntity
import com.seif.ecommerceapp.data.remote.models.*
import com.seif.ecommerceapp.utils.NetworkResult
import kotlinx.coroutines.flow.Flow

interface Repository {
    // remote
    suspend fun createUser(user: SignupRequest): NetworkResult<SignupResponse>?
    suspend fun loginUser(user: LoginRequest): NetworkResult<LoginResponse>?
    suspend fun fetchProducts(): NetworkResult<List<Product>>?
    // local
    suspend fun insertOrders(orderEntity: OrderEntity)
    fun readOrders(): Flow<List<OrderEntity>>
    suspend fun deleteOrder(orderEntity:OrderEntity)

}