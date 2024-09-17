package com.unsplash.stockwalls.data.repository.impl

import com.unsplash.stockwalls.api.UnsplashApi
import com.unsplash.stockwalls.data.model.UnsplashPhotoItemDto
import com.unsplash.stockwalls.data.repository.contract.PhotoListRepository
import javax.inject.Inject

class PhotoListRepositoryImpl @Inject constructor(
    private val unsplashApi: UnsplashApi
) : PhotoListRepository {

    override suspend fun getPhotoList(pageNo: Int): List<UnsplashPhotoItemDto> {
        return unsplashApi.getPhotoList(pageNo)
    }
}
