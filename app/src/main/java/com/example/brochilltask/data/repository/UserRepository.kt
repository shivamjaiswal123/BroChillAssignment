package com.example.brochilltask.data.repository

import com.example.brochilltask.data.model.UserRequest
import com.example.brochilltask.data.model.UserResponse
import com.example.brochilltask.data.remote.RetrofitHelper
import retrofit2.Response

class UserRepository {
    companion object{

        suspend fun registerUser(userRequest: UserRequest): Response<UserResponse> {
            return RetrofitHelper.userApiService.register(userRequest)
        }

        suspend fun loginUser(userRequest: UserRequest): Response<UserResponse> {
            return RetrofitHelper.userApiService.login(userRequest)
        }
    }

}