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
import com.seif.ecommerceapp.databinding.CartRowBinding


class CartAdapter(private val listdata: List<CartData>):
    RecyclerView.Adapter<CartAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private lateinit var binding : CartRowBinding

        val tvName: TextView
        val tvDetails:TextView
        val ivclothes:ImageView

        init {
            tvName = view.findViewById<TextView>(R.id.textView2)
            tvDetails = view.findViewById<TextView>(R.id.textView)
            ivclothes = view.findViewById<ImageView>(R.id.imageView)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.cart_row, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val cart_item = listdata[position]
        holder.tvName.text = cart_item.name
        Log.d("here",cart_item.name)

    }

    override fun getItemCount(): Int = listdata.size
}

