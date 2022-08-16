package com.seif.ecommerceapp.utils

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.seif.ecommerceapp.R
import com.seif.ecommerceapp.data.remote.models.CartData
import com.seif.ecommerceapp.data.remote.models.Product
import com.seif.ecommerceapp.databinding.CartRowBinding
import com.seif.ecommerceapp.databinding.ItemProductRowBinding


class CartAdapter(private val listdata: ArrayList<CartData>):
    RecyclerView.Adapter<CartAdapter.ViewHolder>() {
    class ViewHolder(private val binding: CartRowBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(listdata: CartData) {
            binding.textView2.text = listdata.name
            binding.textView.text = listdata.totalprice().toString()
            binding.imageView.setImageResource(R.drawable.ic_email)
            Log.d("here",listdata.name)

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        Log.d("here","ok")
        return ViewHolder(
            CartRowBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ),)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bind(listdata[position])
        Log.d("here",listdata[position].name)

    }

    override fun getItemCount()= listdata.size
}

