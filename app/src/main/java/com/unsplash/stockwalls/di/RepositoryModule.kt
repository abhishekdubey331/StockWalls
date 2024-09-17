package com.unsplash.stockwalls.di

import com.unsplash.stockwalls.api.UnsplashApi
import com.unsplash.stockwalls.data.repository.impl.PhotoListRepositoryImpl
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
    @ViewModelScoped
    fun providePhotoListRepository(
        unsplashApi: UnsplashApi,
        @IoDispatcher coroutineDispatcher: CoroutineDispatcher
    ): PhotoListRepository = PhotoListRepositoryImpl(unsplashApi, coroutineDispatcher)
}
