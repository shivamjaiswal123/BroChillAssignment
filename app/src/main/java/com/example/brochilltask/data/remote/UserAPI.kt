package com.example.brochilltask.data.remote

import com.example.brochilltask.data.model.UserRequest
import com.example.brochilltask.data.model.UserResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface UserAPI {

    @POST("/register")
    suspend fun register(@Body userRequest: UserRequest): Response<UserResponse>

    @POST("/login")
    suspend fun login(@Body userRequest: UserRequest): Response<UserResponse>

}