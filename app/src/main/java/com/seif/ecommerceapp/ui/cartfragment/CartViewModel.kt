package com.seif.ecommerceapp.ui.cartfragment

import androidx.lifecycle.*
import com.seif.ecommerceapp.data.local.entities.OrderEntity
import com.seif.ecommerceapp.data.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    val productRepository: ProductRepository
): ViewModel() {
    val readOrders: LiveData<List<OrderEntity>> = productRepository.readOrders().asLiveData()

    fun deleteOrder(order: OrderEntity){
        viewModelScope.launch(Dispatchers.IO) {
            productRepository.deleteOrder(order)
        }
    }
     fun calculateTotalPrice(orderList:List<OrderEntity>):Double {
        var totalPrice = 0.0
        orderList.onEach {
            totalPrice += (it.price * it.amount)
        }
        return totalPrice
    }
}