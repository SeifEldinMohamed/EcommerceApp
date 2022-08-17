package com.seif.ecommerceapp.data.remote.models

data class LoginResponse(
    val username:String,
    val email: String,
    val token:String
)
