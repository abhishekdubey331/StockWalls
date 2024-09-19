package com.unsplash.stockwalls.di

import androidx.paging.PagingConfig
import com.unsplash.stockwalls.api.UnsplashApi
import com.unsplash.stockwalls.data.repository.impl.PhotoListRepositoryImpl
import com.unsplash.stockwalls.data.repository.paging.UnsplashPagingSource
import com.unsplash.stockwalls.domain.contract.repository.PhotoListRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineDispatcher

@Module
@InstallIn(ViewModelComponent::class)
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

    @Provides
    @ViewModelScoped
    fun providePhotoListRepository(
        unsplashPagingSource: UnsplashPagingSource,
        pagingConfig: PagingConfig,
        @IoDispatcher coroutineDispatcher: CoroutineDispatcher
    ): PhotoListRepository = PhotoListRepositoryImpl(
        unsplashPagingSource = unsplashPagingSource,
        dispatcher = coroutineDispatcher,
        pagingConfig = pagingConfig
    )
}
