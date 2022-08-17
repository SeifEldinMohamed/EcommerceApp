package com.seif.ecommerceapp.ui.loginfragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.seif.ecommerceapp.HomeActivity
import com.seif.ecommerceapp.R
import com.seif.ecommerceapp.data.local.sharedpreference.AppSharedPreference
import com.seif.ecommerceapp.data.remote.models.LoginRequest
import com.seif.ecommerceapp.data.remote.models.LoginResponse
import com.seif.ecommerceapp.databinding.FragmentLoginBinding
import com.seif.ecommerceapp.utils.*
import com.seif.ecommerceapp.utils.CommonFunctions.Companion.showSnackBar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {
    lateinit var binding: FragmentLoginBinding
    lateinit var loginViewModel: LoginViewModel
    lateinit var dialog: AlertDialog
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
        loginViewModel = ViewModelProvider(requireActivity())[LoginViewModel::class.java]

        binding.btnLogin.setOnClickListener {
            createAlertDialog()
            AppSharedPreference.init(requireContext())
            loginUser()
        }

        binding.tvRegister.setOnClickListener {
            binding.root.findNavController()
                .navigate(R.id.action_loginFragment_to_registrationFragment)
        }

    }

    private fun loginUser() {
        val email = binding.etEmail.text.toString()
        val password = binding.etPassword.text.toString()
        val user = LoginRequest(email, password)

        if (validateUserInput(email, password)) {
            loginViewModel.loginUser(user)
            observeLoginUserResponse()
        } else {
            Log.d("login", "not valid input")
        }
    }

    private fun observeLoginUserResponse() {
        loginViewModel.loginUserResponse.observe(requireActivity()) {
            it?.let {
                handleNetworkResponse(it)
            }
        }
    }

    private fun handleNetworkResponse(response: NetworkResult<LoginResponse>) {
        when (response) {
            is NetworkResult.Success -> {
                response.data?.let {
                    dismissLoadingDialog()

                    showSnackBar(binding.root, "Welcome ♥")
                    Log.d("login", "user entered ${it.toString()}")

                    AppSharedPreference.writeStringFromSharedPreference("email", it.email)
                    AppSharedPreference.writeStringFromSharedPreference("token", it.token)
                    AppSharedPreference.writeStringFromSharedPreference("username", it.username)

                    startActivity(Intent(requireContext(), HomeActivity::class.java))
                    requireActivity().finish() // to finish start activity
                }
            }
            is NetworkResult.Loading -> {
                Log.d("login", "loading")
                startLoadingDialog()
            }
            is NetworkResult.Error -> {
                Log.d(
                    "login",
                    "from handleNetworkResponse -> Error: ${response.message} ${response.data.toString()}"
                )
                dismissLoadingDialog()
            }
        }
    }

    private fun validateUserInput(email: String, password: String): Boolean {
        return email.isEmail() && password.isPassword()
    }

    fun createAlertDialog() {
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