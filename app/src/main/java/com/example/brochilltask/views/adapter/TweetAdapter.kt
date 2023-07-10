package com.example.brochilltask.views.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.brochilltask.data.model.TweetResponse
import com.example.brochilltask.databinding.ItemRowBinding

class TweetAdapter : RecyclerView.Adapter<TweetAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    inner class ViewHolder(private val binding: ItemRowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(tweet: TweetResponse) {
            binding.apply {
                if (tweet.tweet != null) {
                    tvTweets.text = tweet.tweet
                } else {
                    container.visibility = View.GONE
                }
            }
        }
    }

    private val differCallback = object : DiffUtil.ItemCallback<TweetResponse>() {
        override fun areItemsTheSame(oldItem: TweetResponse, newItem: TweetResponse): Boolean {
            return oldItem._id == newItem._id
        }

        override fun areContentsTheSame(oldItem: TweetResponse, newItem: TweetResponse): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)
}