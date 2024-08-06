package com.example.youtubeapp.ui.fragments.playlistItem

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.youtubeapp.data.model.BaseResponse
import com.example.youtubeapp.data.repository.Repository
import com.example.youtubeapp.utils.Resource
import kotlinx.coroutines.launch

class PlaylistItemViewModel(private val repository: Repository) : ViewModel() {

    private val _playlists = MutableLiveData<Resource<BaseResponse>>()
    val playlists: LiveData<Resource<BaseResponse>> get() = _playlists

    fun getPlaylists() {
        viewModelScope.launch {
            repository.getPlaylist().observeForever {
                _playlists.postValue(it)
            }
        }
    }
}