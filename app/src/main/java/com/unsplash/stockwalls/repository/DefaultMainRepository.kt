package com.unsplash.stockwalls.repository

import com.unsplash.stockwalls.data.UnsplashPhoto
import com.unsplash.stockwalls.di.ApiInterface
import com.unsplash.stockwalls.utils.Resource
import javax.inject.Inject

class DefaultMainRepository @Inject constructor(
    private val unsplashApi: ApiInterface
) : MainRepository {

    override suspend fun getPhotoList(pageNo: Int): Resource<UnsplashPhoto> {
        return try {
            val response = unsplashApi.getPhotoList(pageNo)
            val result = response.body()
            if (response.isSuccessful && result != null) {
                Resource.Success(result)
            } else {
                Resource.Error(response.message())
            }
        } catch (e: Exception) {
            Resource.Error(e.message ?: "An error occurred")
        }
    }
}