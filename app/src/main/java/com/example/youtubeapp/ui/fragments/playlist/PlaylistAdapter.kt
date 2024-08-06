package com.example.youtubeapp.ui.fragments.playlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.youtubeapp.data.model.Item
import com.example.youtubeapp.databinding.ItemPlaylistBinding

class PlaylistAdapter :
    ListAdapter<Item, PlaylistAdapter.PlaylistViewHolder>(PlaylistItemCallback()) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PlaylistViewHolder {
        val binding =
            ItemPlaylistBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PlaylistViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PlaylistAdapter.PlaylistViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    inner class PlaylistViewHolder(private val binding: ItemPlaylistBinding) :
        RecyclerView.ViewHolder(binding.root){
            fun onBind(item: Item) = with(binding){
                tvTitle.text = item.snippet.title

                Glide.with(binding.root)
                    .load(item.snippet.thumbnails.high.url)
                    .into(imgPlaylist)
            }
        }

}

class PlaylistItemCallback : DiffUtil.ItemCallback<Item>() {
    override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean = oldItem.id == newItem.id


    override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean = oldItem == newItem

}