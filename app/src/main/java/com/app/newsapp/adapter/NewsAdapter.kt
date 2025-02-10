package com.app.newsapp.adapter

import com.app.newsapp.R
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.app.newsapp.model.Article
import com.app.newsapp.model.NewsItem
import com.bumptech.glide.Glide

class NewsAdapter(private val newsList: List<Article>) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    class NewsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val newsTitle: TextView = view.findViewById(R.id.newsTitle)
        val newsDescription: TextView = view.findViewById(R.id.newsDescription)
        val newsImage: ImageView = view.findViewById(R.id.newsImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.news_item, parent, false)
        return NewsViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val news = newsList[position]
        holder.newsTitle.text = news.title ?: "No Title"
        holder.newsDescription.text = news.description ?: "No Description"

        Glide.with(holder.itemView.context)
            .load(news.urlToImage)
            .placeholder(R.drawable.ic_launcher_background)
            .into(holder.newsImage)
    }

    override fun getItemCount() = newsList.size
}