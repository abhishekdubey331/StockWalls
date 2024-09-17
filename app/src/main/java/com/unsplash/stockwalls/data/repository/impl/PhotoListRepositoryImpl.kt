package com.unsplash.stockwalls.data.repository.impl

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.unsplash.stockwalls.api.UnsplashApi
import com.unsplash.stockwalls.data.repository.paging.UnsplashPagingSource
import com.unsplash.stockwalls.di.IoDispatcher
import com.unsplash.stockwalls.domain.contract.repository.PhotoListRepository
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flowOn

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
    ).flow.flowOn(ioDispatcher)

    companion object {
        private const val PAGE_SIZE = 20
    }
}
