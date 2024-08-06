package com.example.youtubeapp.ui.fragments.playlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.youtubeapp.data.model.BaseResponse
import com.example.youtubeapp.data.repository.Repository
import com.example.youtubeapp.utils.Resource
import kotlinx.coroutines.launch

class PlaylistViewModel(private val repository: Repository) : ViewModel() {

    private val _playlists = MutableLiveData<Resource<BaseResponse>>()
    val playlists: LiveData<Resource<BaseResponse>> get() = _playlists

    fun getPlaylists(playlistId: String) {
        viewModelScope.launch {
            repository.getPlaylistItems(playlistId).observeForever {
                _playlists.postValue(it)
            }
        }
    }
}