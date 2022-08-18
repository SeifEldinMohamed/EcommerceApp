package com.seif.ecommerceapp.ui.productdetailsfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.seif.ecommerceapp.R
import com.seif.ecommerceapp.data.local.entities.OrderEntity
import com.seif.ecommerceapp.data.remote.models.Product
import com.seif.ecommerceapp.databinding.FragmentProductDetailsBinding
import com.seif.ecommerceapp.utils.Size
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductDetailsFragment : Fragment() {
    private lateinit var binding: FragmentProductDetailsBinding
    private val args: ProductDetailsFragmentArgs by navArgs()
    private var productSize = Size.Small
    lateinit var productDetailsViewModel: ProductDetailsViewModel
    lateinit var productDetails: Product
    var productAmount = 1

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
        productDetailsViewModel =
            ViewModelProvider(requireActivity())[ProductDetailsViewModel::class.java]
        productDetails = args.product

        fillProductsDetails()
        handleBackArrowClick()
        handleSizeSelection()

        binding.btnAddToCart.setOnClickListener {
            Snackbar.make(binding.root, "Added to Cart Successfully", Snackbar.LENGTH_SHORT).show()
            insertOrder()

        }
        binding.ivCart.setOnClickListener {
            binding.root.findNavController()
                .navigate(R.id.action_productDetailsFragment_to_cartFragment)
        }


        binding.btnPlus.setOnClickListener {
            if (productAmount in 1..49) {
                binding.tvAmount.text = (++productAmount).toString()
            }
        }

        binding.btnMinus.setOnClickListener {
            if (productAmount in 2..50) {
                binding.tvAmount.text = (--productAmount).toString()
            }
        }
    }

    private fun insertOrder() {
        val orderEntity = OrderEntity(
            0,
            productDetails.productName,
            productAmount,
            productDetails.price,
            productDetails.description,
            productDetails.photo,
            productSize.name
        )
        productDetailsViewModel.insertOrder(orderEntity)
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


    private fun fillProductsDetails() {
        Picasso.get().load(productDetails.photo).into(binding.ivProductDetails)
        binding.tvPriceDetails.text = "${productDetails.price.toString()} $"
        binding.tvProductNameDetails.text = productDetails.productName
        binding.tvProductDescription.text = productDetails.description
    }

}

