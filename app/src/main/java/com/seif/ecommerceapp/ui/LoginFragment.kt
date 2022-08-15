package com.seif.ecommerceapp.ui

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.seif.ecommerceapp.HomeActivity
import com.seif.ecommerceapp.R
import com.seif.ecommerceapp.databinding.FragmentLoginBinding


class LoginFragment : Fragment() {
lateinit var binding:FragmentLoginBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnLogin.setOnClickListener {
            startActivity(Intent(requireContext(), HomeActivity::class.java))
            requireActivity().finish()
        }

    }
}