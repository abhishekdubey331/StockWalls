package com.unsplash.stockwalls.domain.contract

import com.unsplash.stockwalls.common.ResultState
import com.unsplash.stockwalls.data.UnsplashPhotoItem
import kotlinx.coroutines.flow.Flow

interface FetchPhotosUseCase {
    operator fun invoke(pageNo: Int): Flow<ResultState<List<UnsplashPhotoItem>>>
}