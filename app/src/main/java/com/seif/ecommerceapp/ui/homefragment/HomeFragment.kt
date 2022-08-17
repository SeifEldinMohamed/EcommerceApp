package com.seif.ecommerceapp.ui.homefragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.seif.ecommerceapp.data.remote.models.Product
import com.seif.ecommerceapp.databinding.FragmentHomeBinding
import com.seif.ecommerceapp.utils.NetworkResult
import com.seif.ecommerceapp.utils.observeOnce
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    lateinit var binding: FragmentHomeBinding
    private val productsAdapter: ProductAdapter by lazy { ProductAdapter() }
    lateinit var homeViewModel: HomeViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeViewModel = ViewModelProvider(requireActivity())[HomeViewModel::class.java]

        observeProducts()
        setUpRecyclerView()

    }

    private fun observeProducts() {
        homeViewModel.productsResponse.observe(requireActivity()){
            it?.let {
                handleNetworkResponse(it)
            }
        }
    }


    private fun handleNetworkResponse(response: NetworkResult<List<Product>>) {
        when (response) {
            is NetworkResult.Success -> {
                response.data?.let {
                    Log.d("products", "observe data ${it.toString()}")
                    productsAdapter.addProducts(it)
                }
            }
            is NetworkResult.Loading -> {
                Log.d("products", "loading")
            }
            is NetworkResult.Error -> {
                Log.d("products", "Error: ${response.message}")
            }
        }
    }

//    private fun createDummyData(): ArrayList<Product> {
//        val products = ArrayList<Product>()
//        products.add(
//            Product(
//                1,
//                "TWD T-shirt",
//                "Good cotton t-shirt with great price",
//                "220$",
//                "S",
//                "https://picsum.photos/id/237/200/300"
//            )
//        )
//        products.add(
//            Product(
//                2,
//                "Plain T-shirt",
//                "Good cotton plain t-shirt with great price",
//                "120$",
//                "L",
//                "https://picsum.photos/id/238/200/300"
//            )
//        )
//        products.add(
//            Product(
//                3,
//                "Negan T-shirt",
//                "Good cotton t-shirt with great price",
//                "120$",
//                "M",
//                "https://picsum.photos/id/236/200/300"
//            )
//        )
//        products.add(
//            Product(
//                4,
//                "T-shirt",
//                "Good cotton plain t-shirt with great price",
//                "160$",
//                "L",
//                "https://picsum.photos/id/239/200/300"
//            )
//        )
//        products.add(
//            Product(
//                5,
//                "Rick T-shirt",
//                "Good cotton t-shirt with great price",
//                "180$",
//                "M",
//                "https://picsum.photos/id/233/200/300"
//            )
//        )
//        return products
//    }

    private fun setUpRecyclerView() {
        binding.rvProducts.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.rvProducts.adapter = productsAdapter
    }

}