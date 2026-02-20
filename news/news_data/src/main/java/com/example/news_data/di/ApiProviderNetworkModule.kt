package com.example.news_data.di

import com.example.news_data.netwrok.NewsApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object ApiProviderNetworkModule {

    @Provides
    fun provideNewsApiService(retrofit: Retrofit) : NewsApiService {
        return retrofit.create(NewsApiService::class.java)
    }

}