package com.example.youtubeapp.data.model

import com.google.gson.annotations.SerializedName


data class Standard(
    @SerializedName("url")
    val url: String,
    @SerializedName("width")
    val width: Int,
    @SerializedName("height")
    val height: Int
)