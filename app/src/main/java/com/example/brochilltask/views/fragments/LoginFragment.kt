package com.example.brochilltask.views.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.brochilltask.R
import com.example.brochilltask.data.model.UserRequest
import com.example.brochilltask.databinding.FragmentLoginBinding
import com.example.brochilltask.viewmodel.AuthViewModel

class LoginFragment : Fragment() {
    lateinit var binding: FragmentLoginBinding
    lateinit var authViewModel: AuthViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        authViewModel = ViewModelProvider(this)[AuthViewModel::class.java]

        //shared pref
        val sharedPref = requireActivity().getSharedPreferences("myPrefs", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()

        //navigate to register screen
        binding.txtRegister.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registrationFragment)
        }

        //user login
        authViewModel.userLogin.observe(viewLifecycleOwner, Observer {
            if (it.isSuccessful) {
                editor.apply {
                    putString("token", it.body()?.token.toString())
                    commit()
                }
                val action = LoginFragmentDirections.actionLoginFragmentToHomeFragment(it.body()!!.token)
                findNavController().navigate(action)
            } else {
                Toast.makeText(requireContext(), "Invalid Credentials", Toast.LENGTH_SHORT).show()
            }
        })

        binding.btnNext.setOnClickListener {
            val email = binding.email.text.toString()
            val pass = binding.password.text.toString()

            //validate email/password
            if(validateInput(email, pass)){
                val userRequest = UserRequest("", "", email, pass)
                authViewModel.loginUser(userRequest)
            }
        }
        return binding.root
    }

    private fun validateInput(email: String, pass: String): Boolean {
        if (email.isEmpty() || pass.isEmpty()) {
            Toast.makeText(requireContext(), "Email/password can not be empty", Toast.LENGTH_SHORT)
                .show()
            return false
        }
        return true
    }
}