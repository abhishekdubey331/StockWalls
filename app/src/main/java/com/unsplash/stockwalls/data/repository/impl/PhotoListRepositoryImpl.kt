package com.unsplash.stockwalls.data.repository.impl

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.map
import com.unsplash.stockwalls.api.UnsplashApi
import com.unsplash.stockwalls.data.repository.paging.UnsplashPagingSource
import com.unsplash.stockwalls.di.IoDispatcher
import com.unsplash.stockwalls.domain.contract.repository.PhotoListRepository
import com.unsplash.stockwalls.ui.mapper.toPhotoUIModel
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

class PhotoListRepositoryImpl @Inject constructor(
    private val unsplashApi: UnsplashApi,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val pagingConfig: PagingConfig = PagingConfig(
        pageSize = PAGE_SIZE,
        enablePlaceholders = false
    )
) : PhotoListRepository {

    override fun getPhotoList() = Pager(
        config = pagingConfig,
        pagingSourceFactory = { UnsplashPagingSource(unsplashApi) }
    ).flow
        .map { pagingData ->
            pagingData.map { photoDto ->
                photoDto.toPhotoUIModel()
            }
        }
        .flowOn(ioDispatcher)

    companion object {
        private const val PAGE_SIZE = 20
    }
}
