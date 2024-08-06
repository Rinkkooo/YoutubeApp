package com.example.youtubeapp.data.repository.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.youtubeapp.utils.Resource
import kotlinx.coroutines.Dispatchers
import okhttp3.Dispatcher
import retrofit2.HttpException
import java.io.IOException

abstract class BaseRepository {
    protected fun <T> doRequest(request: suspend () -> T): LiveData<Resource<T>> =
        liveData(Dispatchers.IO) {
            emit(Resource.Loading())
            try {
                val response = request()
                if (response != null) {
                    emit(Resource.Success(response))
                } else {
                    emit(Resource.Error("Error"))
                }
            } catch (
                e: Exception
            ) {
                emit(Resource.Error(handleException(e)))
            }
        }

    private fun handleException(e: Exception): String {
        return when (e) {
            is IOException -> e.localizedMessage ?: "Network Error"
            is HttpException -> e.localizedMessage ?: "Server Error"
            else -> e.localizedMessage ?: "Error"
        }
    }
}