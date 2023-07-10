package com.example.brochilltask.data.remote

import com.example.brochilltask.data.model.Tweet
import com.example.brochilltask.data.model.TweetResponse
import com.example.brochilltask.data.model.Welcome
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface TweetAPI {
    @GET("/tweets")
    suspend fun getAllTweets(@Header("x-api-key") token: String): Response<List<TweetResponse>>

    @POST("/tweets")
    suspend fun postTweet(@Header("x-api-key") token: String, @Body tweet: Tweet): Response<TweetResponse>

    @GET("/welcome")
    suspend fun showWelcomeMsg(@Header("x-api-key") token: String): Response<Welcome>
}