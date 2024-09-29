package com.unsplash.stockwalls.di

import com.unsplash.stockwalls.domain.contract.repository.PhotosRepository
import com.unsplash.stockwalls.domain.contract.usecase.FetchPhotoByIdUseCase
import com.unsplash.stockwalls.domain.contract.usecase.FetchPhotosUseCase
import com.unsplash.stockwalls.domain.impl.FetchPhotoByIdUseCaseImpl
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
        photosRepository: PhotosRepository
    ): FetchPhotosUseCase = FetchPhotosUseCaseImpl(
        photosRepository
    )

    @Provides
    fun providesFetchPhotoByIdUseCase(
        photosRepository: PhotosRepository
    ): FetchPhotoByIdUseCase = FetchPhotoByIdUseCaseImpl(
        photosRepository
    )
}
