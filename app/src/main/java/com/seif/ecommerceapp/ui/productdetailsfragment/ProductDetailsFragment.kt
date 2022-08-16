package com.seif.ecommerceapp.ui.productdetailsfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.seif.ecommerceapp.R
import com.seif.ecommerceapp.databinding.FragmentProductDetailsBinding
import com.seif.ecommerceapp.utils.Size
import com.squareup.picasso.Picasso

class ProductDetailsFragment : Fragment() {
    private lateinit var binding: FragmentProductDetailsBinding
    private val args: ProductDetailsFragmentArgs by navArgs()
    private var productAmount = 1
    private var productSize = Size.Small
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

        fillProductsDetails()
        handleAmountOfProduct()
        handleBackArrowClick()
        handleSizeSelection()

        binding.btnAddToCart.setOnClickListener {
            Snackbar.make(binding.root, "Added to Cart Successfully", Snackbar.LENGTH_SHORT).show()
        }
        binding.ivCart.setOnClickListener {
            binding.root.findNavController().navigate(R.id.action_productDetailsFragment_to_cartFragment)
        }
    }

    private fun handleSizeSelection() {
        binding.radioGroup.setOnCheckedChangeListener { _, checkedId ->
            if (checkedId == R.id.radio_small) {
                productSize = Size.Small
            }
            if (checkedId == R.id.radio_medium) {
                productSize = Size.Medium
            }
            if (checkedId == R.id.radio_large) {
                productSize = Size.Large
            }
        }
    }

    private fun handleBackArrowClick() {
        binding.ivBackArrow.setOnClickListener {
            binding.root.findNavController().navigateUp()
        }
    }

    private fun handleAmountOfProduct() {
        binding.tvAmount.text = productAmount.toString()
        binding.btnPlus.setOnClickListener {
            if (productAmount in 1..49)
                binding.tvAmount.text = (++productAmount).toString()
        }

        binding.btnMinus.setOnClickListener {
            if (productAmount in 2..50)
                binding.tvAmount.text = (--productAmount).toString()
        }
    }

    private fun fillProductsDetails() {
        val productDetails = args.product
        Picasso.get().load(productDetails.image).into(binding.ivProductDetails)
        binding.tvPriceDetails.text = productDetails.price
        binding.tvProductNameDetails.text = productDetails.name
        binding.tvProductDescription.text = productDetails.description
    }

}

