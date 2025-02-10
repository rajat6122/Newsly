package com.app.newsapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.newsapp.adapter.NewsAdapter
import com.app.newsapp.model.NewsItem

class HomeFragment : Fragment() {

    private lateinit var adapter: NewsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)

        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        adapter = NewsAdapter(getSampleNews()) // Assign adapter
        recyclerView.adapter = adapter // Set adapter
        return view
    }

    private fun getSampleNews(): List<NewsItem> {
        return listOf(
            NewsItem("Breaking News", "This is a sample news description", R.drawable.ic_launcher_background),
            NewsItem("Political News", "Latest updates on Politics", R.drawable.ic_launcher_background),
            NewsItem("Tech News", "Latest updates on technology", R.drawable.ic_launcher_background),
            NewsItem("Bitcoin News", "Latest updates on Bitcoin", R.drawable.ic_launcher_background)
        )
    }
}