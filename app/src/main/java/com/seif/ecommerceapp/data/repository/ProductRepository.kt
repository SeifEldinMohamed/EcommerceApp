package com.seif.ecommerceapp.data.repository

import com.seif.ecommerceapp.data.remote.RemoteDataSource
import com.seif.ecommerceapp.data.remote.models.Product
import com.seif.ecommerceapp.data.remote.models.SignupRequest
import com.seif.ecommerceapp.data.remote.models.SignupResponse
import com.seif.ecommerceapp.utils.NetworkResult
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

@ActivityRetainedScoped // we will have the same instance even when we rotate screen
class ProductRepository @Inject constructor(
private val remoteDataSource: RemoteDataSource
):Repository {
    override suspend fun createUser(user: SignupRequest): NetworkResult<SignupResponse>? {
        return remoteDataSource.createUser(user)
    }

    override suspend fun fetchProducts(): NetworkResult<List<Product>>? {
        return remoteDataSource.fetchProducts()
    }
}