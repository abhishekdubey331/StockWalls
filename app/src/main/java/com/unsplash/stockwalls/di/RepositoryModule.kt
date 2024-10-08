package com.unsplash.stockwalls.di

import androidx.paging.PagingConfig
import com.unsplash.stockwalls.api.UnsplashApi
import com.unsplash.stockwalls.data.repository.impl.PhotosRepositoryImpl
import com.unsplash.stockwalls.data.repository.paging.UnsplashPagingSource
import com.unsplash.stockwalls.domain.contract.repository.PhotosRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import kotlinx.coroutines.CoroutineDispatcher

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun providesUnsplashPagingSource(unsplashApi: UnsplashApi): UnsplashPagingSource {
        return UnsplashPagingSource(unsplashApi)
    }

    @Provides
    fun providesPagingConfig(): PagingConfig {
        val pageSize = 20
        return PagingConfig(
            pageSize = pageSize,
            enablePlaceholders = false
        )
    }

    @Singleton
    @Provides
    fun providePhotoRepository(
        unsplashApi: UnsplashApi,
        unsplashPagingSource: UnsplashPagingSource,
        pagingConfig: PagingConfig,
        @IoDispatcher coroutineDispatcher: CoroutineDispatcher
    ): PhotosRepository = PhotosRepositoryImpl(
        unsplashPagingSource = unsplashPagingSource,
        dispatcher = coroutineDispatcher,
        networkSource = unsplashApi,
        pagingConfig = pagingConfig
    )
}
