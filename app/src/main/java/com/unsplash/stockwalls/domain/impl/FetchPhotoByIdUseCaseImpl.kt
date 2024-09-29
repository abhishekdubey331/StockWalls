package com.unsplash.stockwalls.domain.impl

import com.unsplash.stockwalls.domain.contract.repository.PhotosRepository
import com.unsplash.stockwalls.domain.contract.usecase.FetchPhotoByIdUseCase
import com.unsplash.stockwalls.ui.mapper.PhotoUIModel
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class FetchPhotoByIdUseCaseImpl @Inject constructor(
    private val repository: PhotosRepository
) : FetchPhotoByIdUseCase {

    override operator fun invoke(photoId: String): Flow<PhotoUIModel> {
        return repository.getPhotoById(photoId)
    }
}
