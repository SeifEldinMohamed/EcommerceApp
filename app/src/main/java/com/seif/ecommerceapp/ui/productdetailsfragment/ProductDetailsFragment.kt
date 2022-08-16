package com.seif.ecommerceapp.ui.productdetailsfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.seif.ecommerceapp.R
import com.seif.ecommerceapp.databinding.FragmentProductDetailsBinding

class ProductDetailsFragment : Fragment() {
private lateinit var binding: FragmentProductDetailsBinding
// private val args : ProductDetailsFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentProductDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // val productDetails = args.product
    }

}