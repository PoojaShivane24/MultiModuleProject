package com.example.news_presenter

import androidx.lifecycle.ViewModel
import com.example.news_domain.use_case.GetNewsArticleUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(private val getNewsArticleUseCase: GetNewsArticleUseCase) : ViewModel() {

    val _newsArticle = MutableStateFlow(NewsState())

    val newsArticle : StateFlow<NewsState>
        get() {
            return _newsArticle
        }

}