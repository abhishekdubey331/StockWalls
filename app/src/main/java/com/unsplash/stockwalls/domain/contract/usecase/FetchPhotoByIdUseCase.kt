package com.unsplash.stockwalls.domain.contract.usecase

import com.unsplash.stockwalls.ui.mapper.PhotoUIModel
import kotlinx.coroutines.flow.Flow

interface FetchPhotoByIdUseCase {
    operator fun invoke(photoId: String): Flow<PhotoUIModel>
}
