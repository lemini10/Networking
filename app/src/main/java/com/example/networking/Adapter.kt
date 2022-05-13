package com.example.networking

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import org.w3c.dom.Text

class Adapter(val articles: List<ResultClass>,
              private val onItemClicked: (position: Int) -> Unit): RecyclerView.Adapter<MainViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.article_row, parent, false)
        return MainViewHolder(v,onItemClicked)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val article: ResultClass = articles.get(position)
        holder.render(article)
    }

    override fun getItemCount(): Int {
        return articles.count()
    }
}

class MainViewHolder(
    val view: View,
    private val onItemClicked: (position: Int) -> Unit): RecyclerView.ViewHolder(view), View.OnClickListener {

    init {
        view.setOnClickListener(this)
    }

    fun render(article: ResultClass) {
        view.findViewById<ImageView>(R.id.imageView)

        view.findViewById<TextView>(R.id.titleTextView).text = article.title
        view.findViewById<TextView>(R.id.dateTextView).text = article.published_date
        Picasso.get().load(article.media.first().mediaMetaData.first().url).into(view.findViewById<ImageView>(R.id.imageView))

    }

    override fun onClick(p0: View?) {
        val position = adapterPosition
        onItemClicked(position)
    }
}