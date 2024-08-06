package com.example.youtubeapp.di

import com.example.youtubeapp.data.repository.Repository
import org.koin.dsl.module

val repositoryModule = module {
    factory {
        Repository(get())
    }
}