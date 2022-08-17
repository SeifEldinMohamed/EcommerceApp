package com.seif.ecommerceapp.ui.cartfragment

import com.seif.ecommerceapp.data.local.entities.OrderEntity
import com.seif.ecommerceapp.data.remote.models.Product

interface OnDeleteOrderClickListener {
    fun onDeleteOrderClick(orderEntity: OrderEntity)
}