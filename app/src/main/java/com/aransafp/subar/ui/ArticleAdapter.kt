package com.aransafp.subar.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aransafp.core.domain.model.Article
import com.aransafp.core.utils.DateHelper
import com.aransafp.subar.databinding.ItemsArticleBinding
import com.bumptech.glide.Glide

class ArticleAdapter : RecyclerView.Adapter<ArticleAdapter.ViewHolder>() {

    private val listArticle = ArrayList<Article>()

    private lateinit var onItemClickListener: OnItemClickListener

    fun setArticles(listArticle: List<Article>?) {
        if (listArticle != null) {
            this.listArticle.clear()
            this.listArticle.addAll(listArticle)
            notifyDataSetChanged()
        }

    }

    fun setOnItemClickCallback(onItemClickListener: OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemsArticleBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listArticle[position])
    }

    override fun getItemCount(): Int = listArticle.size

    inner class ViewHolder(private val binding: ItemsArticleBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(article: Article) {

            with(binding) {

                tvTitle.text = article.title
                tvDescription.text = article.description
                tvAuthor.text = article.author

                val date = DateHelper.dateFormat(article.publishedAt as String)
                tvDate.text = date

                Glide.with(itemView)
                    .load(article.urlToImage)
                    .into(imgPoster)

                itemView.setOnClickListener { onItemClickListener.onItem(article.id) }
            }
        }
    }

    interface OnItemClickListener {
        fun onItem(articleId: Int)
    }
}