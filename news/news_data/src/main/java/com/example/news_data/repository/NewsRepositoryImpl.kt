package com.example.news_data.repository

import com.example.news_data.mapper.toDomainArticle
import com.example.news_data.netwrok.NewsApiService
import com.example.news_domain.model.Article
import com.example.news_domain.repository.NewsRepository

class NewsRepositoryImpl(private val newsApiService: NewsApiService) : NewsRepository {
    override suspend fun getNewsArticle(): List<Article> {
        return newsApiService.getNewsArticles().articles.map { it.toDomainArticle() }
    }
}