package com.unsplash.stockwalls.data.repository.contract

import com.unsplash.stockwalls.data.model.UnsplashPhotoItemDto

interface PhotoListRepository {
    suspend fun getPhotoList(pageNo: Int): List<UnsplashPhotoItemDto>
}
