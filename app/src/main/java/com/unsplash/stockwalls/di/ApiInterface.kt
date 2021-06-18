package com.unsplash.stockwalls.di

import com.unsplash.stockwalls.data.UnsplashPhoto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("photos")
    suspend fun getPhotoList(@Query("page") pageNo: Int): Response<UnsplashPhoto>
}