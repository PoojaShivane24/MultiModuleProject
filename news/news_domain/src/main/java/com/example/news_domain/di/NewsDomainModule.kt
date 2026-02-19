package com.example.news_domain.di

import com.example.news_domain.repository.NewsRepository
import com.example.news_domain.use_case.GetNewsArticleUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class NewsDomainModule {


    @Provides
    fun provideGetNewsArticleUseCase(newsRepository: NewsRepository) : GetNewsArticleUseCase {
        return GetNewsArticleUseCase(newsRepository)
    }
}