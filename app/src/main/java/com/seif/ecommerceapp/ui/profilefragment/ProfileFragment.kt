package com.seif.ecommerceapp.ui.profilefragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.seif.ecommerceapp.R
import com.seif.ecommerceapp.data.local.sharedpreference.AppSharedPreference
import com.seif.ecommerceapp.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {
lateinit var binding: FragmentProfileBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        AppSharedPreference.init(requireContext())

        AppSharedPreference.readStringFromSharedPreference("address","null")?.let {
            binding.etAddress.setText(it)
        }
        AppSharedPreference.readStringFromSharedPreference("username","null")?.let {
            binding.etUsername.setText(it)
        }
        AppSharedPreference.readStringFromSharedPreference("email","null")?.let {
            binding.etEmail.setText(it)
        }
    }

}