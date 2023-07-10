package com.example.brochilltask.views.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.brochilltask.databinding.FragmentHomeBinding
import com.example.brochilltask.viewmodel.TweetViewModel
import com.example.brochilltask.views.adapter.TweetAdapter

class HomeFragment : Fragment() {
    lateinit var binding: FragmentHomeBinding
    lateinit var tweetViewModel: TweetViewModel
    lateinit var tweetAdapter: TweetAdapter
    private val args: HomeFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        tweetViewModel = ViewModelProvider(this)[TweetViewModel::class.java]

        setupRecyclerView()
        val token = args.token

        //show all tweets in home screen
        tweetViewModel.tweets.observe(viewLifecycleOwner, Observer {
            tweetAdapter.differ.submitList(it)
        })

        tweetViewModel.getAllTweets(token)

        //open tweet screen
        binding.floatingBtn.setOnClickListener{
            val action = HomeFragmentDirections.actionHomeFragmentToTweetFragment(token)
            findNavController().navigate(action)
        }

        return binding.root
    }

    private fun setupRecyclerView() {
        tweetAdapter = TweetAdapter()
        binding.recyclerview.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = tweetAdapter
        }
    }
}