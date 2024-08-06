package com.example.youtubeapp.di

import com.example.youtubeapp.ui.fragments.playlist.PlaylistViewModel
import com.example.youtubeapp.ui.fragments.playlistItem.PlaylistItemViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel {
        PlaylistViewModel(get())
    }

    viewModel {
        PlaylistItemViewModel(get())
    }

}