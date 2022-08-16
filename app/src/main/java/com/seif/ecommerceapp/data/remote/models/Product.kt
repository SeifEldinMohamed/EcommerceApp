package com.seif.ecommerceapp.data.remote.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Product(
    val id:Int,
    val name:String,
    val description:String,
    val price: String,
    val size: String,
    val image: String
): Parcelable
