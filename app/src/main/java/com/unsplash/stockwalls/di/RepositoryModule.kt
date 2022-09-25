package com.unsplash.stockwalls.di

import com.unsplash.stockwalls.api.UnsplashApi
import com.unsplash.stockwalls.data.repository.contract.PhotoListRepository
import com.unsplash.stockwalls.data.repository.impl.PhotoListRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @Provides
    @ViewModelScoped
    fun providePhotoListRepository(
        unsplashApi: UnsplashApi
    ): PhotoListRepository = PhotoListRepositoryImpl(unsplashApi)
}
