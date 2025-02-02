package com.example.youtubeapp.data.model

import com.google.gson.annotations.SerializedName

data class Localized(
    @SerializedName("title")
    val title: String,
    @SerializedName("description")
    val description: String
)