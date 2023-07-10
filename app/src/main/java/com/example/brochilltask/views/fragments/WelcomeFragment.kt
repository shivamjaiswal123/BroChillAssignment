package com.example.brochilltask.views.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.brochilltask.R
import com.example.brochilltask.databinding.FragmentWelcomeBinding
import com.example.brochilltask.viewmodel.TweetViewModel

class WelcomeFragment : Fragment() {
    private lateinit var binding: FragmentWelcomeBinding
    lateinit var tweetViewModel: TweetViewModel
    private val args: WelcomeFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentWelcomeBinding.inflate(inflater, container, false)
        tweetViewModel = ViewModelProvider(this)[TweetViewModel::class.java]

        val token = args.token

        //show welcome message
        tweetViewModel.msg.observe(viewLifecycleOwner, Observer {
            binding.welcomeMsg.text = it.message
        })

        tweetViewModel.showMsg(token)

        binding.btnStart.setOnClickListener {
            val action = WelcomeFragmentDirections.actionWelcomeFragmentToHomeFragment(token)
            findNavController().navigate(action)
        }
        return binding.root
    }
}