package com.seif.ecommerceapp.ui.introfragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.seif.ecommerceapp.HomeActivity
import com.seif.ecommerceapp.R
import com.seif.ecommerceapp.data.local.sharedpreference.AppSharedPreference
import com.seif.ecommerceapp.databinding.ActivityStartBinding
import com.seif.ecommerceapp.databinding.FragmentIntroBinding


class IntroFragment : Fragment() {

    private lateinit var binding: FragmentIntroBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentIntroBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (checkUserLogin()) {
            startActivity(Intent(requireContext(), HomeActivity::class.java))
            requireActivity().finish()
        }

        binding.btnLogin.setOnClickListener {
            binding.root.findNavController().navigate(R.id.action_introFragment_to_loginFragment)
        }

        binding.btnRegister.setOnClickListener {
            binding.root.findNavController()
                .navigate(R.id.action_introFragment_to_rigestrationFragment)
        }
    }

    private fun checkUserLogin(): Boolean {
        AppSharedPreference.init(requireContext())
        var loginStatus = ""
        AppSharedPreference.readStringFromSharedPreference("login_status", "false")?.let {
            loginStatus = it
        }
        if (loginStatus == "false")
            return false

        return true
    }

}