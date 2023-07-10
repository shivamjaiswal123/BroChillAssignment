package com.example.brochilltask.data.repository

import android.util.Log
import com.example.brochilltask.data.model.Tweet
import com.example.brochilltask.data.model.TweetResponse
import com.example.brochilltask.data.model.Welcome
import com.example.brochilltask.data.remote.RetrofitHelper
import retrofit2.Response

class TweetRepository {
    companion object{
        suspend fun getAllTweets(token: String): List<TweetResponse>? {
            val response =  RetrofitHelper.tweetApiService.getAllTweets(token)
            if(response.isSuccessful){
                return response.body()
            }
            return null
        }

        suspend fun postTweet(token: String, tweet: Tweet): Response<TweetResponse> {
            return RetrofitHelper.tweetApiService.postTweet(token, tweet)
        }

        suspend fun showWelcomeMsg(token: String): Welcome? {
            val response = RetrofitHelper.tweetApiService.showWelcomeMsg(token)
            if(response.isSuccessful){
                return response.body()
            }
            return null
        }
    }
}