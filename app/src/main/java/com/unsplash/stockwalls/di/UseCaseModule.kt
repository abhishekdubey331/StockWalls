package com.unsplash.stockwalls.di

import com.unsplash.stockwalls.data.repository.contract.PhotoListRepository
import com.unsplash.stockwalls.domain.contract.FetchPhotosUseCase
import com.unsplash.stockwalls.domain.impl.FetchPhotosUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import kotlinx.coroutines.CoroutineDispatcher

@InstallIn(ViewModelComponent::class)
@Module
object UseCaseModule {

    @Provides
    fun provideFetchPhotosUseCase(
        photoListRepository: PhotoListRepository,
        @IoDispatcher coroutineDispatcher: CoroutineDispatcher
    ): FetchPhotosUseCase = FetchPhotosUseCaseImpl(
        photoListRepository,
        coroutineDispatcher
    )
}
