package com.seif.ecommerceapp.data.remote.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Product(
    val description:String,
    val id:Int,
    val photo: String,
    val productName:String,
    val price: Double,
    val size: String,
): Parcelable
