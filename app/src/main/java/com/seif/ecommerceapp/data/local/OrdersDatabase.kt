package com.seif.ecommerceapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.seif.ecommerceapp.data.local.entities.OrderEntity

@Database(
    entities = [OrderEntity::class],
    version = 1,
    exportSchema = false
)
abstract class OrdersDatabase: RoomDatabase() {

    abstract fun orderDao(): OrderDao

} // we will make our database builder in di package