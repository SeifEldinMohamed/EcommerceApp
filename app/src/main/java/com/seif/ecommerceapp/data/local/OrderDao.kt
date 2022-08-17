package com.seif.ecommerceapp.data.local

import androidx.room.*
import com.seif.ecommerceapp.data.local.entities.OrderEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface OrderDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrder(orderEntity:OrderEntity)

    @Query("SELECT * FROM order_table")
    fun readAllOrders(): Flow<List<OrderEntity>>

    @Delete
    suspend fun deleteOrder(orderEntity:OrderEntity)
}