package com.example.news_data.netwrok

import com.example.common_utils.Constants
import com.example.news_data.model.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {

    @GET("top-headlines")
    suspend fun getNewsArticles(@Query("country") country : String = Constants.COUNTRY,
                        @Query("category") category : String = Constants.CATEGORY,
                        @Query("apiKey") apiKey : String = Constants.API_KEY
    ) : NewsResponse
}