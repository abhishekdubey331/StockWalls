package com.unsplash.stockwalls.di

import com.unsplash.stockwalls.BuildConfig
import com.unsplash.stockwalls.api.UnsplashApi
import com.unsplash.stockwalls.common.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofitApi(okHttpClient: OkHttpClient): UnsplashApi = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
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
                    .addQueryParameter(Constants.AUTHORIZATION, BuildConfig.ACCESS_KEY)
                    .build()
                chain.proceed(chain.request().newBuilder().url(url).build())
            }.build()
    }
}
