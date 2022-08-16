package com.seif.ecommerceapp.ui.cartfragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.seif.ecommerceapp.data.remote.models.CartData
import com.seif.ecommerceapp.databinding.FragmentCartBinding
import com.seif.ecommerceapp.utils.CartAdapter


class CartFragment : Fragment() {

    private lateinit var binding : FragmentCartBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCartBinding.inflate(inflater, container, false)
        return binding.root
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val myListData: ArrayList<CartData> = ArrayList<CartData>()
        myListData.add(CartData("boom","boom",3,1.5))
        myListData.add(CartData("boom","boom",3,1.5))
        myListData.add(CartData("boom","boom",3,1.5))

        binding.rvCart.setHasFixedSize(true)
        binding.rvCart.layoutManager = LinearLayoutManager(requireContext())

        binding.rvCart.adapter = CartAdapter(myListData)


    }


}