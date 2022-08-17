package com.seif.ecommerceapp.ui.productdetailsfragment

import androidx.lifecycle.*
import com.seif.ecommerceapp.data.local.entities.OrderEntity
import com.seif.ecommerceapp.data.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailsViewModel @Inject constructor(
    val productRepository: ProductRepository
): ViewModel() {

    fun insertOrder(order: OrderEntity){
        viewModelScope.launch(Dispatchers.IO) {
            productRepository.insertOrders(order)
        }
    }
}