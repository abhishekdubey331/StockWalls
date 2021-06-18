package com.unsplash.stockwalls.di

import com.unsplash.stockwalls.BuildConfig
import com.unsplash.stockwalls.repository.DefaultMainRepository
import com.unsplash.stockwalls.repository.MainRepository
import com.unsplash.stockwalls.utils.DispatcherProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

private const val BASE_URL = "https://api.unsplash.com/"
private const val AUTHORIZATION = "Authorization"

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Singleton
    @Provides
    fun providesApi(): ApiInterface = createWebService(
        okHttpClient = createHttpClient(),
        baseUrl = BASE_URL
    )

    @Singleton
    @Provides
    fun provideMainRepository(api: ApiInterface): MainRepository = DefaultMainRepository(api)

    @Singleton
    @Provides
    fun provideDispatchers(): DispatcherProvider = object : DispatcherProvider {
        override val main: CoroutineDispatcher
            get() = Dispatchers.Main
        override val io: CoroutineDispatcher
            get() = Dispatchers.IO
        override val default: CoroutineDispatcher
            get() = Dispatchers.Default
        override val unconfined: CoroutineDispatcher
            get() = Dispatchers.Unconfined
    }

    private fun createHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .addInterceptor(Interceptor { chain ->
                val builder = chain.request().newBuilder()
                builder.header(AUTHORIZATION, BuildConfig.ACCESS_KEY)
                return@Interceptor chain.proceed(builder.build())
            }).build()
    }

    private inline fun <reified T> createWebService(
        okHttpClient: OkHttpClient,
        baseUrl: String
    ): T {
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
        return retrofit.create(T::class.java)
    }
}