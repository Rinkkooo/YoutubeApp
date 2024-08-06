package com.example.youtubeapp.data.api

import com.example.youtubeapp.data.model.BaseResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("playlist")
    suspend fun getPlaylist(
        @Query("part") part: String,
        @Query("channelId") channelId: String,
        @Query("key") apiKey: String,
        @Query("maxResults") maxResults: Int
    ): BaseResponse

    @GET("playlistItems")
    suspend fun getPlaylistItems(
        @Query("part") part: String,
        @Query("playlistId") playlistId: String,
        @Query("key") apiKey: String,
        @Query("maxResults") maxResults: Int
    ): BaseResponse
}