package com.seif.ecommerceapp.data.remote

import com.seif.ecommerceapp.data.local.sharedpreference.AppSharedPreference
import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .newBuilder()
            .addHeader(
                "Authorization",
                " Bearer ${AppSharedPreference.readStringFromSharedPreference("token", "")}"
                    ).build()
        return chain.proceed(request)
    }

}