package com.seif.ecommerceapp.utils

import androidx.recyclerview.widget.DiffUtil
import com.seif.ecommerceapp.data.local.entities.OrderEntity

class OrdersDiffUtil(
    private val oldList: List<OrderEntity>,
    private val newList: List<OrderEntity>
): DiffUtil.Callback(){
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] === newList[newItemPosition]
    }
}