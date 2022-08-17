package com.seif.ecommerceapp.ui.cartfragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.seif.ecommerceapp.R
import com.seif.ecommerceapp.data.remote.models.CartData
import com.seif.ecommerceapp.databinding.FragmentCartBinding
import com.seif.ecommerceapp.utils.CartAdapter
import dagger.hilt.android.qualifiers.ApplicationContext

class CartFragment : Fragment() {

    private lateinit var binding : FragmentCartBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_cart, container, false)
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCartBinding.inflate(layoutInflater)
        val myListData: List<CartData> = List<CartData>(10){CartData("boom","boom",3,2.5)}


        val recyclerView =  binding.recyclerView
        val adapter = CartAdapter(myListData)

        //recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this@CartFragment.context)

        recyclerView.adapter = adapter


    }


}