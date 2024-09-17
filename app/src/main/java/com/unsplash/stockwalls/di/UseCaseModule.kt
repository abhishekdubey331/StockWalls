package com.unsplash.stockwalls.di

import com.unsplash.stockwalls.domain.contract.repository.PhotoListRepository
import com.unsplash.stockwalls.domain.contract.usecase.FetchPhotosUseCase
import com.unsplash.stockwalls.domain.impl.FetchPhotosUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@InstallIn(ViewModelComponent::class)
@Module
object UseCaseModule {

    @Provides
    fun provideFetchPhotosUseCase(
        photoListRepository: PhotoListRepository
    ): FetchPhotosUseCase = FetchPhotosUseCaseImpl(
        photoListRepository
    )
}
