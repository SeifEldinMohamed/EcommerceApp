package com.seif.ecommerceapp.data.local

import com.seif.ecommerceapp.data.local.entities.OrderEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class LocalDataSource @Inject constructor(
    private val orderDao: OrderDao
){
    suspend fun insertOrders(orderEntity: OrderEntity) {
        orderDao.insertOrder(orderEntity)
    }
    fun readOrders(): Flow<List<OrderEntity>> {
        return orderDao.readAllOrders()
    }
    suspend fun deleteOrder(orderEntity:OrderEntity){
        orderDao.deleteOrder(orderEntity)
    }

}