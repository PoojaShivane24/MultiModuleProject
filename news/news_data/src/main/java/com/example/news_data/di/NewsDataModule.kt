package com.example.news_data.di

import com.example.news_data.netwrok.NewsApiService
import com.example.news_data.repository.NewsRepositoryImpl
import com.example.news_domain.repository.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object NewsDataModule {

    @Provides
    fun provideNewsApiService(retrofit: Retrofit) : NewsApiService {
        return retrofit.create(NewsApiService::class.java)
    }

    @Provides
    fun provideNewsRepository(newsApiService: NewsApiService) : NewsRepository {
        return NewsRepositoryImpl(newsApiService)
    }

}