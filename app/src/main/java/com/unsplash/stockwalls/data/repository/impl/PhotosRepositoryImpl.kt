package com.unsplash.stockwalls.data.repository.impl

import android.util.Log
import android.util.LruCache
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.map
import com.unsplash.stockwalls.api.UnsplashApi
import com.unsplash.stockwalls.data.repository.paging.UnsplashPagingSource
import com.unsplash.stockwalls.di.IoDispatcher
import com.unsplash.stockwalls.domain.contract.repository.PhotosRepository
import com.unsplash.stockwalls.ui.mapper.PhotoUIModel
import com.unsplash.stockwalls.ui.mapper.toPhotoUIModel
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

class PhotosRepositoryImpl @Inject constructor(
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    private val networkSource: UnsplashApi,
    private val unsplashPagingSource: UnsplashPagingSource,
    private val pagingConfig: PagingConfig
) : PhotosRepository {

    private val photoCache = object : LruCache<String, PhotoUIModel>(500) {}

    override fun getPhotoList() = Pager(config = pagingConfig, pagingSourceFactory = {
        unsplashPagingSource
    }).flow.map { pagingData ->
        pagingData.map { photoDto ->
            val photoUIModel = photoDto.toPhotoUIModel()
            photoCache.put(photoUIModel.id, photoUIModel)
            photoUIModel
        }
    }.flowOn(dispatcher)

    override fun getPhotoById(photoId: String) = flow {
        val cachedPhoto = photoCache.get(photoId)
        if (cachedPhoto != null) {
            emit(cachedPhoto)
            Log.d("PhotosRepositoryImpl", "getPhotoById: $photoId from cache")
        } else {
            val fetchedPhoto = networkSource.getPhotoById(photoId).toPhotoUIModel()
            photoCache.put(photoId, fetchedPhoto)
            Log.d("PhotosRepositoryImpl", "getPhotoById: $photoId from network")
            emit(fetchedPhoto)
        }
    }.flowOn(dispatcher)
}
