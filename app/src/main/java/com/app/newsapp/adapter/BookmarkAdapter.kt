package com.app.newsapp.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.app.newsapp.R
import com.app.newsapp.model.Article
import com.bumptech.glide.Glide

class BookmarkAdapter(
    private val context: Context,
    private val bookmarkedArticles: MutableList<Article>,
    private val onRemoveBookmark: (Article) -> Unit
) : RecyclerView.Adapter<BookmarkAdapter.BookmarkViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookmarkViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_bookmarked_news, parent, false)
        return BookmarkViewHolder(view)
    }

    override fun onBindViewHolder(holder: BookmarkViewHolder, position: Int) {
        val article = bookmarkedArticles[position]
        holder.bookmarkTitle.text = article.title

        // Load the article image using Glide
        Glide.with(context)
            .load(article.urlToImage) // Assuming urlToImage is a property in your Article model
            .into(holder.bookmarkImage) // Set the image into the ImageView

        // Set OnClickListener to open the article URL
        holder.itemView.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(article.url)) // Assuming url is a property in your Article model
            context.startActivity(intent)
        }

        holder.bookmarkRemoveButton.setOnClickListener {
            onRemoveBookmark(article)
            Toast.makeText(context, "Bookmark Removed", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int = bookmarkedArticles.size

    class BookmarkViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val bookmarkTitle: TextView = itemView.findViewById(R.id.bookmarkTitle)
        val bookmarkImage: ImageView = itemView.findViewById(R.id.bookmarkImage) // Add this line
        val bookmarkRemoveButton: ImageView = itemView.findViewById(R.id.bookmarkRemoveButton)
    }
}