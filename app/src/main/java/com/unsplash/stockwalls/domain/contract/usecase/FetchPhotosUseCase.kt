package com.unsplash.stockwalls.domain.contract.usecase

import androidx.paging.PagingData
import com.unsplash.stockwalls.data.model.UnsplashPhotoItemDto
import kotlinx.coroutines.flow.Flow

interface FetchPhotosUseCase {
    operator fun invoke(): Flow<PagingData<UnsplashPhotoItemDto>>
}
