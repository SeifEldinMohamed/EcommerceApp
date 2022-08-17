package com.seif.ecommerceapp.data.repository

import com.seif.ecommerceapp.data.local.LocalDataSource
import com.seif.ecommerceapp.data.local.entities.OrderEntity
import com.seif.ecommerceapp.data.remote.RemoteDataSource
import com.seif.ecommerceapp.data.remote.models.*
import com.seif.ecommerceapp.utils.NetworkResult
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ActivityRetainedScoped // we will have the same instance even when we rotate screen
class ProductRepository @Inject constructor(
private val remoteDataSource: RemoteDataSource,
private val localDataSource: LocalDataSource
):Repository {
    override suspend fun createUser(user: SignupRequest): NetworkResult<SignupResponse>? {
        return remoteDataSource.createUser(user)
    }

    override suspend fun loginUser(user: LoginRequest): NetworkResult<LoginResponse>? {
        return remoteDataSource.loginUser(user)
    }

    override suspend fun fetchProducts(): NetworkResult<List<Product>>? {
        return remoteDataSource.fetchProducts()
    }

    // local
    override suspend fun insertOrders(orderEntity: OrderEntity) {
        localDataSource.insertOrders(orderEntity)
    }

    override fun readOrders(): Flow<List<OrderEntity>> {
        return localDataSource.readOrders()
    }

    override suspend fun deleteOrder(orderEntity: OrderEntity) {
        localDataSource.deleteOrder(orderEntity)
    }


}