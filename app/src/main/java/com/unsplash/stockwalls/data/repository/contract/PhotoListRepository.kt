package com.unsplash.stockwalls.data.repository.contract

import com.unsplash.stockwalls.data.UnsplashPhotoItem

interface PhotoListRepository {
    suspend fun getPhotoList(pageNo: Int): List<UnsplashPhotoItem>
}