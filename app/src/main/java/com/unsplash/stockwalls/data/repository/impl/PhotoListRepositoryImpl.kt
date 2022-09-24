package com.unsplash.stockwalls.data.repository.impl

import com.unsplash.stockwalls.api.UnsplashApi
import com.unsplash.stockwalls.data.UnsplashPhotoItem
import com.unsplash.stockwalls.data.repository.contract.PhotoListRepository
import javax.inject.Inject

class PhotoListRepositoryImpl @Inject constructor(
    private val unsplashApi: UnsplashApi
) : PhotoListRepository {

    override suspend fun getPhotoList(pageNo: Int): List<UnsplashPhotoItem> {
        return unsplashApi.getPhotoList(pageNo)
    }
}