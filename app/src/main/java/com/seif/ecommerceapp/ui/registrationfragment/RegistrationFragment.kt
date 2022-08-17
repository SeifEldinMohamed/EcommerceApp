package com.seif.ecommerceapp.ui.registrationfragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.seif.ecommerceapp.R
import com.seif.ecommerceapp.data.remote.models.SignupRequest
import com.seif.ecommerceapp.data.remote.models.SignupResponse
import com.seif.ecommerceapp.databinding.FragmentRegistrationBinding
import com.seif.ecommerceapp.utils.*
import com.seif.ecommerceapp.utils.CommonFunctions.Companion.showSnackBar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegistrationFragment : Fragment() {
    lateinit var binding: FragmentRegistrationBinding
    lateinit var registrationViewModel: RegistrationViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentRegistrationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        registrationViewModel = ViewModelProvider(requireActivity())[RegistrationViewModel::class.java]


        binding.btnRegister.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()
            val name = binding.etUsername.text.toString()
            val user = SignupRequest(email, name, password)

            if (validateUserInput(name, email, password)) {
                registrationViewModel.createUser(user)
                observeCreateUserResponse()
            }
            else {
                Log.d("register","not valid input")
            }
        }
    }

    private fun observeCreateUserResponse() {
        registrationViewModel.createUserResponse.observe(requireActivity()) {
            it?.let {
                handleNetworkResponse(it)
            }
        }

    }

    private fun handleNetworkResponse(response: NetworkResult<SignupResponse>) {
        when (response) {
            is NetworkResult.Success -> {
                response.data?.let {
                    showSnackBar(binding.root, it.toString())
                    Log.d("register", "created successfully ${it.toString()}")
                    binding.root.findNavController().navigate(R.id.action_registrationFragment_to_loginFragment)
//                    startActivity(Intent(requireContext(), HomeActivity::class.java))
//                    requireActivity().finish()
                }
            }
            is NetworkResult.Loading -> {
                Log.d("register", "loading")
            }
            is NetworkResult.Error -> {
                Log.d("register", "Error: ${response.message}")
            }
        }
    }


    private fun validateUserInput(username: String, email: String, password: String): Boolean {
        return username.isUsername() && email.isEmail() && password.isPassword()
    }

}