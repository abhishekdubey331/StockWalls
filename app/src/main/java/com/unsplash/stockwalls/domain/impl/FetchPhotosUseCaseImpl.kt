package com.unsplash.stockwalls.domain.impl

import androidx.paging.PagingData
import com.unsplash.stockwalls.data.model.UnsplashPhotoItemDto
import com.unsplash.stockwalls.domain.contract.repository.PhotoListRepository
import com.unsplash.stockwalls.domain.contract.usecase.FetchPhotosUseCase
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class FetchPhotosUseCaseImpl @Inject constructor(
    private val repository: PhotoListRepository
) : FetchPhotosUseCase {

    override fun invoke(): Flow<PagingData<UnsplashPhotoItemDto>> {
        return repository.getPhotoList()
    }
}
