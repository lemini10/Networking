package com.example.networking

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.webkit.WebView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: Adapter
    private val articlesList: MutableList<ResultClass> = mutableListOf<ResultClass>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initRecyclerView()
        getEmailPopular()
    }

    private fun getRetrofit():Retrofit {
        return  Retrofit.Builder().baseUrl("https://api.nytimes.com/svc/mostpopular/v2/")
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    private fun getEmailPopular(){
        CoroutineScope(Dispatchers.IO).launch {
            val call: Response<ResponseClass> = getRetrofit().create(APIServiceInterface::class.java).getResponse("emailed/7.json?api-key=fjwzNrG0DRrOiOKzN3EerzrMDQC74wxU")
            val articles: ResponseClass? = call.body()
            val articlesFetched: List<ResultClass> = articles?.results ?: emptyList()
            runOnUiThread {
                if (call.isSuccessful) {
                    if (articles != null) {
                        articlesList.clear()
                        articlesList.addAll(articlesFetched)
                        adapter.notifyDataSetChanged()
                    }
                }
            }
        }
    }

    fun didTapButton(view: View) {
        getEmailPopular()
    }

    fun initRecyclerView() {
        adapter = Adapter(articlesList, { position -> handleClick(position)})
        findViewById<RecyclerView>(R.id.recyclerView).layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        findViewById<RecyclerView>(R.id.recyclerView).adapter = adapter
    }

    fun handleClick(position: Int) {
        val myWebView: WebView = findViewById(R.id.webView)
        val url = articlesList[position].url
        myWebView.loadUrl(url)
    }

}