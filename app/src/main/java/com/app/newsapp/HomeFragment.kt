package com.app.newsapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.newsapp.adapter.NewsAdapter
import com.app.newsapp.model.Article
import com.app.newsapp.model.NewsItem
import com.app.newsapp.model.NewsResponse
import com.app.newsapp.retrofitapi.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {

    private val newsList = mutableListOf<Article>()
    private lateinit var adapter: NewsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)

        adapter = NewsAdapter(newsList)

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter // Set adapter

        fetchNews()
        return view
    }

    private fun fetchNews() {
        RetrofitClient.instance.getNews("bitcoin").enqueue(object :
            Callback<NewsResponse> {
            override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                if (response.isSuccessful && response.body() != null) {
                    newsList.clear()
                    newsList.addAll(response.body()!!.articles)
                    adapter.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                Toast.makeText(requireContext(), "Failed to fetch news", Toast.LENGTH_SHORT).show()
            }

        }

        )
    }

//    private fun getSampleNews(): List<NewsItem> {
//        return listOf(
//            NewsItem(
//                "Breaking News",
//                "This is a sample news description",
//                R.drawable.ic_launcher_background
//            ),
//            NewsItem(
//                "Political News",
//                "Latest updates on Politics",
//                R.drawable.ic_launcher_background
//            ),
//            NewsItem(
//                "Tech News",
//                "Latest updates on technology",
//                R.drawable.ic_launcher_background
//            ),
//            NewsItem("Bitcoin News", "Latest updates on Bitcoin", R.drawable.ic_launcher_background)
//        )
//    }
}