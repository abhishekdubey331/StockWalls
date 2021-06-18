package com.unsplash.stockwalls.di

import com.unsplash.stockwalls.data.UnsplashPhoto
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {

    @GET("photos?page=1")
    suspend fun getPhotoList(): Response<UnsplashPhoto>
}