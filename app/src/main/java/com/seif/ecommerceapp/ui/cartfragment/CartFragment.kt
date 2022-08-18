package com.seif.ecommerceapp.ui.cartfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.seif.ecommerceapp.data.local.entities.OrderEntity
import com.seif.ecommerceapp.data.remote.models.Product
import com.seif.ecommerceapp.databinding.FragmentCartBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CartFragment : Fragment(), OnDeleteOrderClickListener {
    private lateinit var binding: FragmentCartBinding
    private val ordersAdapter: OrdersAdapter by lazy { OrdersAdapter() }
    lateinit var cartViewModel: CartViewModel
    lateinit var orderList: List<OrderEntity>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentCartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cartViewModel = ViewModelProvider(requireActivity())[CartViewModel::class.java]

        setUpRecyclerView()
        ordersAdapter.addView(binding.root)
        ordersAdapter.addListener(this)

        //  ordersAdapter.addOrders(createDummyData())
        readOrders()

    }

    private fun readOrders() {
        cartViewModel.readOrders.observe(requireActivity()) {
            it?.let {
                ordersAdapter.addOrders(it)
                binding.tvSubtotal.text = "${cartViewModel.calculateTotalPrice(it)} $"
                if (it.isEmpty()) {
                    binding.tvEmptyCart.visibility = View.VISIBLE
                    binding.ivEmptyCart.visibility = View.VISIBLE
                    binding.rvOrderedProducts.visibility = View.GONE
                } else {
                    binding.tvEmptyCart.visibility = View.GONE
                    binding.ivEmptyCart.visibility = View.GONE
                    binding.rvOrderedProducts.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun createDummyData(): ArrayList<Product> {
        val products = ArrayList<Product>()
        products.add(
            Product(
                "Good cotton t-shirt with great price",
                1,
                "https://picsum.photos/id/237/200/300",
                "TWD T-shirt",
                20.0,
                "S",
            )
        )
        products.add(
            Product(
                "Good cotton t-shirt with great price",
                1,
                "https://picsum.photos/id/237/200/300",
                "TWD T-shirt",
                20.0,
                "S",
            )
        )
        products.add(
            Product(
                "Good cotton t-shirt with great price",
                1,
                "https://picsum.photos/id/237/200/300",
                "TWD T-shirt",
                20.0,
                "S",
            )
        )
        products.add(
            Product(
                "Good cotton t-shirt with great price",
                1,
                "https://picsum.photos/id/237/200/300",
                "TWD T-shirt",
                20.0,
                "S",
            )
        )
        products.add(
            Product(
                "Good cotton t-shirt with great price",
                1,
                "https://picsum.photos/id/237/200/300",
                "TWD T-shirt",
                20.0,
                "S",
            )
        )
        products.add(
            Product(
                "Good cotton t-shirt with great price",
                1,
                "https://picsum.photos/id/237/200/300",
                "TWD T-shirt",
                20.0,
                "S",
            )
        )

        return products
    }

    private fun setUpRecyclerView() {
        binding.rvOrderedProducts.layoutManager = LinearLayoutManager(requireContext())
        binding.rvOrderedProducts.adapter = ordersAdapter
    }

    override fun onDeleteOrderClick(orderEntity: OrderEntity) {
        cartViewModel.deleteOrder(orderEntity)
        readOrders()
    }

}