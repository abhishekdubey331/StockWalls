package com.unsplash.stockwalls.api

import com.unsplash.stockwalls.data.model.UnsplashPhotoItemDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface UnsplashApi {

    @GET("photos")
    suspend fun getPhotoList(@Query("page") pageNo: Int): List<UnsplashPhotoItemDto>

    @GET("photos/{id}")
    suspend fun getPhotoById(@Path("id") photoId: String): UnsplashPhotoItemDto
}
