package com.example.brochilltask.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.brochilltask.data.model.UserRequest
import com.example.brochilltask.data.model.UserResponse
import com.example.brochilltask.data.repository.UserRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class AuthViewModel: ViewModel() {
    private var _userLogin = MutableLiveData<Response<UserResponse>>()
    val userLogin: LiveData<Response<UserResponse>>  get() = _userLogin

    private var _userRegister = MutableLiveData<Response<UserResponse>>()
    val userRegister: LiveData<Response<UserResponse>>  get() = _userRegister


    fun registerUser(userRequest: UserRequest){
        viewModelScope.launch {
            _userRegister.value = UserRepository.registerUser(userRequest)
        }
    }

    fun  loginUser(userRequest: UserRequest){
        viewModelScope.launch {
            _userLogin.value = UserRepository.loginUser(userRequest)
        }
    }

}