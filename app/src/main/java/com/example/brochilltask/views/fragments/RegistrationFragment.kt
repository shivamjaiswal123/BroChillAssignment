package com.example.brochilltask.views.fragments

import android.content.Context
import android.os.Bundle
import android.util.Patterns
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
import com.example.brochilltask.databinding.FragmentRegistrationBinding
import com.example.brochilltask.viewmodel.AuthViewModel

class RegistrationFragment : Fragment() {
    lateinit var binding: FragmentRegistrationBinding
    lateinit var authViewModel: AuthViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRegistrationBinding.inflate(inflater, container, false)
        authViewModel = ViewModelProvider(this)[AuthViewModel::class.java]

        //shared preference
        val sharedPref = requireActivity().getSharedPreferences("myPrefs", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()

        //navigate to login screen
        binding.txtLogin.setOnClickListener {
            findNavController().navigate(R.id.action_registrationFragment_to_loginFragment)
        }

        //register new user
        authViewModel.userRegister.observe(viewLifecycleOwner, Observer {
            if (it.isSuccessful) {
                editor.apply {
                    putString("token", it.body()?.token.toString())
                    commit()
                }
                val action = RegistrationFragmentDirections.actionRegistrationFragmentToWelcomeFragment(it.body()?.token.toString())
                findNavController().navigate(action)
            }else{
                Toast.makeText(requireContext(), "Error occurred !!!", Toast.LENGTH_SHORT).show()
            }
        })

        binding.btnRegNext.setOnClickListener {
            val firstName = binding.firstName.text.toString()
            val lastName = binding.lastName.text.toString()
            val email = binding.regEmail.text.toString()
            val pass = binding.regPassword.text.toString()

            //validate input
            if(validateInput(firstName, lastName, email, pass)){
                val userRequest = UserRequest(firstName, lastName, email, pass)
                authViewModel.registerUser(userRequest)
            }
        }

        //if user is already logged in
        val token = sharedPref.getString("token", null)
        if (token != null) {
            val action = RegistrationFragmentDirections.actionRegistrationFragmentToHomeFragment(token)
            findNavController().navigate(action)
        }

        return binding.root
    }

    private fun validateInput(firstName: String, lastName: String, email: String, pass: String): Boolean{
        if(firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || pass.isEmpty()){
            Toast.makeText(requireContext(), "Please fill all the fields", Toast.LENGTH_SHORT)
                .show()
            return false
        }else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(requireContext(), "Invalid email !!!", Toast.LENGTH_SHORT).show()
            return false
        }else if (pass.length < 6) {
            Toast.makeText(
                requireContext(), "Password should contain more than 5 characters",
                Toast.LENGTH_SHORT
            ).show()
            return false
        }
        return true
    }
}