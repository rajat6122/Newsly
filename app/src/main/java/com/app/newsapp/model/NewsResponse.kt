package com.app.newsapp.model

data class NewsResponse (
    val status: String,
    val totalResult: Int,
    val articles: List<Article>
)