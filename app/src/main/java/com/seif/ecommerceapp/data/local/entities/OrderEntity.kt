package com.seif.ecommerceapp.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "order_table")
data class OrderEntity(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val productName:String,
    val amount:Int,
    val price:Double,
    val description:String,
    val photo :String,
    val size:String
)
