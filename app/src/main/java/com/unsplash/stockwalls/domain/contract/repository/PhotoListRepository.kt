package com.unsplash.stockwalls.domain.contract.repository

import androidx.paging.PagingData
import com.unsplash.stockwalls.data.model.UnsplashPhotoItemDto
import kotlinx.coroutines.flow.Flow

interface PhotoListRepository {
    fun getPhotoList(): Flow<PagingData<UnsplashPhotoItemDto>>
}
