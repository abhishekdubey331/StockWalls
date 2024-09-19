package com.unsplash.stockwalls.data.repository.impl

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.map
import com.unsplash.stockwalls.data.repository.paging.UnsplashPagingSource
import com.unsplash.stockwalls.di.IoDispatcher
import com.unsplash.stockwalls.domain.contract.repository.PhotoListRepository
import com.unsplash.stockwalls.ui.mapper.toPhotoUIModel
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

class PhotoListRepositoryImpl @Inject constructor(
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    private val unsplashPagingSource: UnsplashPagingSource,
    private val pagingConfig: PagingConfig
) : PhotoListRepository {

    override fun getPhotoList() = Pager(
        config = pagingConfig,
        pagingSourceFactory = {
            unsplashPagingSource
        }
    ).flow
        .map { pagingData ->
            pagingData.map { photoDto ->
                photoDto.toPhotoUIModel()
            }
        }
        .flowOn(dispatcher)
}
