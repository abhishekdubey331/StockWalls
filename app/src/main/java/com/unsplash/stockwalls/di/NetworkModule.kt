package com.unsplash.stockwalls.di

import com.unsplash.stockwalls.BuildConfig
import com.unsplash.stockwalls.api.UnsplashApi
import com.unsplash.stockwalls.common.AUTHORIZATION
import com.unsplash.stockwalls.common.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofitApi(okHttpClient: OkHttpClient): UnsplashApi = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .client(okHttpClient)
        .build()
        .create(UnsplashApi::class.java)

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .addInterceptor { chain ->
                val url = chain
                    .request().url
                    .newBuilder()
                    .addQueryParameter(AUTHORIZATION, BuildConfig.ACCESS_KEY)
                    .build()
                chain.proceed(chain.request().newBuilder().url(url).build())
            }.build()
    }
}
