package com.example.news_data.di

import com.example.news_data.netwrok.NewsApiService
import com.example.news_data.repository.NewsRepositoryImpl
import com.example.news_domain.repository.NewsRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class NewsDataModule {

    /*@Provides
    @Singleton
    fun provideNewsRepository(newsApiService: NewsApiService) : NewsRepository {
        return NewsRepositoryImpl(newsApiService)
    }*/
    @Binds
    abstract fun bindNewsRepository(
        impl: NewsRepositoryImpl
    ): NewsRepository

}