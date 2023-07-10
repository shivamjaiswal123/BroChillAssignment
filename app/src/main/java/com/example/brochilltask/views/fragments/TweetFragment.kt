package com.example.brochilltask.views.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.brochilltask.R
import com.example.brochilltask.data.model.Tweet
import com.example.brochilltask.databinding.FragmentTweetBinding
import com.example.brochilltask.viewmodel.TweetViewModel

class TweetFragment : Fragment() {
    lateinit var binding: FragmentTweetBinding
    lateinit var tweetViewModel: TweetViewModel
    private val args: TweetFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentTweetBinding.inflate(inflater, container, false)

        tweetViewModel = ViewModelProvider(this)[TweetViewModel::class.java]
        val token = args.token

        //post a tweet and navigate to home screen
        tweetViewModel.postTweets.observe(viewLifecycleOwner, Observer {
            if(it.isSuccessful){
                val action = TweetFragmentDirections.actionTweetFragmentToHomeFragment(token)
                findNavController().navigate(action)
            }else{
                Toast.makeText(requireContext(), "Error occurred !!!", Toast.LENGTH_SHORT).show()
            }
        })

        binding.btnPost.setOnClickListener {
            val etTweet = binding.etTweet.text.toString()
            tweetViewModel.postTweet(token, Tweet(etTweet))
        }

        return  binding.root
    }
}