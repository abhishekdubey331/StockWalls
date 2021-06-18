package com.unsplash.stockwalls.repository

import com.unsplash.stockwalls.data.UnsplashPhoto
import com.unsplash.stockwalls.utils.Resource

interface MainRepository {
    suspend fun getPhotoList(pageNo: Int): Resource<UnsplashPhoto>
}