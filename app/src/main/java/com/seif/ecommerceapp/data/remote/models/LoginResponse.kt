package com.seif.ecommerceapp.data.remote.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LoginResponse(
    val token:String,
    val email: String,
    val username:String
): Parcelable
