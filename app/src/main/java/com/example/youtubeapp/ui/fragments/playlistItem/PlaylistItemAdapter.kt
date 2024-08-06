package com.example.youtubeapp.ui.fragments.playlistItem

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.youtubeapp.data.model.Item
import com.example.youtubeapp.databinding.ItemVideoBinding

class PlaylistItemAdapter(private val onItemClick: (Item) -> Unit) :
    ListAdapter<Item, PlaylistItemAdapter.VideoViewHolder>(VideoItemCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        val binding = ItemVideoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VideoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class VideoViewHolder(private val binding: ItemVideoBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Item) = with(binding) {
            tvTitle.text = item.snippet.title
            tvSeries.text = "${item.contentDetails.itemCount} video series"

            Glide.with(binding.root)
                .load(item.snippet.thumbnails.high.url)
                .into(imgVideo)

            root.setOnClickListener {
                onItemClick(item)
            }
        }
    }
}

class VideoItemCallback : DiffUtil.ItemCallback<Item>() {
    override fun areItemsTheSame(oldItem: Item, newItem: Item) = oldItem.id == newItem.id
    override fun areContentsTheSame(oldItem: Item, newItem: Item) = oldItem == newItem
}