package com.unsplash.stockwalls.domain.contract.usecase

import androidx.paging.PagingData
import com.unsplash.stockwalls.ui.mapper.PhotoUIModel
import kotlinx.coroutines.flow.Flow

interface FetchPhotosUseCase {
    operator fun invoke(): Flow<PagingData<PhotoUIModel>>
}
