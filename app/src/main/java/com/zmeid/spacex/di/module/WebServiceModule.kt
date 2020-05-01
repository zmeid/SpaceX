package com.zmeid.spacex.di.module

import com.zmeid.spacex.BuildConfig
import com.zmeid.spacex.repository.webservice.LaunchService
import com.zmeid.spacex.util.BASE_URL
import dagger.Module
import dagger.Provides
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Provides web services related objects.
 */
@Module
class WebServiceModule {

    @Provides
    fun providesLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            level =
                if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        }

    @Singleton
    @Provides
    fun providesRetrofit(): Retrofit = Retrofit.Builder().apply {
        baseUrl(BASE_URL)
        addConverterFactory(GsonConverterFactory.create())
    }.build()

    @Singleton
    @Provides
    fun providesLaunchService(retrofit: Retrofit): LaunchService =
        retrofit.create(LaunchService::class.java)
}