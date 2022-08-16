package com.seif.ecommerceapp.ui.homefragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.seif.ecommerceapp.R
import com.seif.ecommerceapp.data.remote.models.Product
import com.seif.ecommerceapp.databinding.ItemProductRowBinding
import com.seif.ecommerceapp.utils.ProductsDiffUtil
import com.squareup.picasso.Picasso

class ProductAdapter: RecyclerView.Adapter<ProductAdapter.MyViewHolder>() {
    private var products: List<Product> = emptyList()
    class MyViewHolder(private val binding: ItemProductRowBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(product: Product) {
            binding.tvProductName.text = product.name
            binding.tvPrice.text = product.price
            Picasso.get().load(product.image).into(binding.ivProduct)

            binding.cvProduct.setOnClickListener {
                val action = HomeFragmentDirections.actionHomeFragmentToProductDetailsFragment(product)
                 binding.root.findNavController().navigate(action)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            ItemProductRowBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(products[position])
    }

    override fun getItemCount()= products.size

    fun addProducts(newProducts: List<Product>){
        val diffUtilCallBack = ProductsDiffUtil(this.products, newProducts)
        val result = DiffUtil.calculateDiff(diffUtilCallBack)
        this.products = newProducts
        result.dispatchUpdatesTo(this)
    }
}