package com.example.youtubeapp.data.repository

import com.example.youtubeapp.data.api.ApiService
import com.example.youtubeapp.data.repository.base.BaseRepository
import com.example.youtubeapp.utils.Constants

class Repository(private val apiService: ApiService) : BaseRepository() {
    fun getPlaylist() = doRequest {
        apiService.getPlaylist(
            part = Constants.PART,
            channelId = Constants.CHANNEL_ID,
            apiKey = Constants.API_KEY,
            maxResults = Constants.MAX_RESULTS
        )
    }

    fun getPlaylistItems(playlistId: String) = doRequest {
        apiService.getPlaylistItems(
            part = Constants.PART,
            playlistId = Constants.CHANNEL_ID,
            apiKey = Constants.API_KEY,
            maxResults = Constants.MAX_RESULTS
        )
    }
}