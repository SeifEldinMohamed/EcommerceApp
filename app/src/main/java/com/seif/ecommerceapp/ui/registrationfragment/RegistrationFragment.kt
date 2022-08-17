package com.seif.ecommerceapp.ui.registrationfragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.seif.ecommerceapp.R
import com.seif.ecommerceapp.data.local.sharedpreference.AppSharedPreference
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
    lateinit var dialog: AlertDialog

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
        AppSharedPreference.init(requireContext())
        registrationViewModel =
            ViewModelProvider(requireActivity())[RegistrationViewModel::class.java]

        binding.btnRegister.setOnClickListener {
            createAlertDialog()
            registerUser()
        }

        binding.tvLogin.setOnClickListener {
            binding.root.findNavController()
                .navigate(R.id.action_registrationFragment_to_loginFragment)
        }
    }

    private fun registerUser() {
        val email = binding.etEmail.text.toString()
        val password = binding.etPassword.text.toString()
        val confirmPassword = binding.etConfirmPassword.text.toString()
        val name = binding.etUsername.text.toString()
        val user = SignupRequest(email, name, password)

        val address = binding.etAddress.text.toString()
        AppSharedPreference.writeStringFromSharedPreference("address", address)

        if (validateUserInput(name, email, password,confirmPassword)) {
            registrationViewModel.createUser(user)
            observeCreateUserResponse()
        } else {
            showSnackBar(binding.root, "Please Enter a Valid Input !")
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
                    dismissLoadingDialog()
                    showSnackBar(binding.root, "Account Created Successfully")
                    Log.d("register", "created successfully ${it.toString()}")
                    binding.root.findNavController()
                        .navigate(R.id.action_registrationFragment_to_loginFragment)
                }
            }
            is NetworkResult.Loading -> {
                Log.d("register", "loading")
                startLoadingDialog()
            }
            is NetworkResult.Error -> {
                Log.d("register", "from handleNetworkResponse -> Error: ${response.message}")
                showSnackBar(binding.root, "Account is already registered !")
                dismissLoadingDialog()
            }
        }
    }

    private fun validateUserInput(username: String, email: String, password: String, confirmPassword:String): Boolean {
        return username.isUsername() && email.isEmail() && password.isPassword() && (password == confirmPassword)
    }

    private fun createAlertDialog() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setView(layoutInflater.inflate(R.layout.custom_loading_dialog, null))
        builder.setCancelable(true)
        dialog = builder.create()
    }
    private fun startLoadingDialog() {
        dialog.create()
        dialog.show()
    }
    private fun dismissLoadingDialog(){
        dialog.dismiss()
    }

}