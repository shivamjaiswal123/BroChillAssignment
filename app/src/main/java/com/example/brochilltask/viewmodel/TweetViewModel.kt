package com.example.brochilltask.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.brochilltask.data.model.Tweet
import com.example.brochilltask.data.model.TweetResponse
import com.example.brochilltask.data.model.Welcome
import com.example.brochilltask.data.repository.TweetRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class TweetViewModel: ViewModel() {

    private var _tweets = MutableLiveData<List<TweetResponse>>()
    val tweets: LiveData<List<TweetResponse>> get() = _tweets

    private var _postTweets = MutableLiveData<Response<TweetResponse>>()
    val postTweets: LiveData<Response<TweetResponse>> get() = _postTweets

    private var _msg = MutableLiveData<Welcome>()
    val msg: MutableLiveData<Welcome> get() = _msg

    fun getAllTweets(token: String){
        viewModelScope.launch {
            TweetRepository.getAllTweets(token)?.let {
                _tweets.value = it
            }
        }
    }

    fun postTweet(token: String, tweet: Tweet){
        viewModelScope.launch {
            _postTweets.value = TweetRepository.postTweet(token, tweet)
        }
    }

    fun showMsg(token: String){
        viewModelScope.launch {
            TweetRepository.showWelcomeMsg(token).let {
                _msg.value = it
            }
        }
    }
}