package com.unsplash.stockwalls.api

import com.unsplash.stockwalls.data.model.UnsplashPhotoItemDto
import retrofit2.http.GET
import retrofit2.http.Query

interface UnsplashApi {

    @GET("photos")
    suspend fun getPhotoList(@Query("page") pageNo: Int): List<UnsplashPhotoItemDto>
}