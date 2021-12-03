package com.test.app.di

import com.test.app.data.network.StocksApi
import com.test.app.data.network.StocksApi.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Hilt's module to get Retrofit's network interface object.
 * This module is required because 'stockApi' doesn't has default constructor.
 */
@InstallIn(SingletonComponent::class)
@Module
object ServiceModule {

    @Provides
    @Singleton
    fun provideApi(): StocksApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(StocksApi::class.java)
    }
}