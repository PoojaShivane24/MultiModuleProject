package com.example.news_data.mapper

import com.example.news_data.model.ArticleDTO
import com.example.news_domain.model.Article

fun ArticleDTO.toDomainArticle() : Article {
    return Article (
        this.author,
        this.content,
        this.description,
        this.title,
        this.urlToImage
    )
}