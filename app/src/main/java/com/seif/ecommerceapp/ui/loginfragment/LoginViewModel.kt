package com.seif.ecommerceapp.ui.loginfragment

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.seif.ecommerceapp.data.remote.models.LoginRequest
import com.seif.ecommerceapp.data.remote.models.LoginResponse
import com.seif.ecommerceapp.data.remote.models.SignupRequest
import com.seif.ecommerceapp.data.remote.models.SignupResponse
import com.seif.ecommerceapp.data.repository.ProductRepository
import com.seif.ecommerceapp.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: ProductRepository,
    application: Application
) : AndroidViewModel(application) {

    /** Retrofit **/
    val loginUserResponse: MutableLiveData<NetworkResult<LoginResponse>> by lazy {
        MutableLiveData()
    }

    fun loginUser(user: LoginRequest) {
        viewModelScope.launch(Dispatchers.IO) {
            loginUserSafeCall(user)
        }
    }

    private suspend fun loginUserSafeCall(user: LoginRequest) {
        loginUserResponse.postValue(NetworkResult.Loading()) // loading state until we get data from api
        if (hasInternetConnection()) {
            Log.d("login", "login with user")
            try {
                repository.loginUser(user)?.let {
                    loginUserResponse.postValue(it)
                }
            } catch (e: Exception) {
                loginUserResponse.postValue(NetworkResult.Error("something went wrong ${e.message}"))
            }
        } else {
            loginUserResponse.postValue(NetworkResult.Error("No Internet Connection"))
        }
    }

    // function to check internet connectivity ( returns true when internet is reliable and it will return false if not
    private fun hasInternetConnection(): Boolean {
        val connectivityManager = getApplication<Application>().getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager

        val activeNetwork: Network = connectivityManager.activeNetwork ?: return false
        val capabilities: NetworkCapabilities =
            connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false

        return when { // return true if there is an internet connection from wifi, cellular and ethernet
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
    }

}