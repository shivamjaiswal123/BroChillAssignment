package com.example.brochilltask.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {
    private const val BASE_URL = "https://wern-api.brochill.app"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val userApiService = retrofit.create(UserAPI::class.java)
    val tweetApiService = retrofit.create(TweetAPI::class.java)
}