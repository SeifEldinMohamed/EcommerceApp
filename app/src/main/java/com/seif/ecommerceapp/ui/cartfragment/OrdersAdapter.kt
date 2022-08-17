package com.seif.ecommerceapp.ui.cartfragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.seif.ecommerceapp.data.local.entities.OrderEntity
import com.seif.ecommerceapp.databinding.ItemOrderedProductRowBinding
import com.seif.ecommerceapp.utils.OrdersDiffUtil
import com.squareup.picasso.Picasso


class OrdersAdapter : RecyclerView.Adapter<OrdersAdapter.MyViewHolder>() {
    private var orderList: List<OrderEntity> = emptyList()
    private lateinit var view:View
    private lateinit var myListener: OnDeleteOrderClickListener

    inner class MyViewHolder(private var binding: ItemOrderedProductRowBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(orderedEntity: OrderEntity) {
            binding.tvProductName.text = orderedEntity.productName
            binding.tvPrice.text = "Price: ${orderedEntity.price.toInt()} $"
            binding.tvAmountOrder.text = "Amount: ${orderedEntity.amount}"
            binding.tvSize.text = "Size: ${orderedEntity.size}"
            Picasso.get().load(orderedEntity.photo).into(binding.ivProduct)

            binding.ivDeleteItem.setOnClickListener {
                myListener.onDeleteOrderClick(orderedEntity)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            ItemOrderedProductRowBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(orderList[position])

    }

    override fun getItemCount(): Int = orderList.size

    fun addOrders(newOrders: List<OrderEntity>) {
        val diffUtilCallback = OrdersDiffUtil(this.orderList, newOrders)
        val result = DiffUtil.calculateDiff(diffUtilCallback)
        this.orderList = newOrders
        result.dispatchUpdatesTo(this)
    }

    fun addView(view: View) {
        this.view = view
    }
    fun addListener(listener: OnDeleteOrderClickListener) {
        myListener = listener
    }

}

