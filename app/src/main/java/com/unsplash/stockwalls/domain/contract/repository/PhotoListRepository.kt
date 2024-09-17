package com.unsplash.stockwalls.domain.contract.repository

import androidx.paging.PagingData
import com.unsplash.stockwalls.ui.mapper.PhotoUIModel
import kotlinx.coroutines.flow.Flow

interface PhotoListRepository {
    fun getPhotoList(): Flow<PagingData<PhotoUIModel>>
}
