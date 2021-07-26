package com.aransafp.subar.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aransafp.core.domain.model.Article
import com.aransafp.subar.databinding.ItemsArticleBinding
import com.bumptech.glide.Glide

class ArticleAdapter : RecyclerView.Adapter<ArticleAdapter.ViewHolder>() {

    private val listArticle = ArrayList<Article>()

    private lateinit var onItemClickListener: OnItemClickListener

    fun setArticles(listArticle: List<Article>?) {
        if (listArticle != null) {
            this.listArticle.clear()
            this.listArticle.addAll(listArticle)
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

        fun bind(artilce: Article) {

            with(binding) {

                tvTitle.text = artilce.title
                tvAuthor.text = artilce.author
                tvDate.text = artilce.publishedAt

                Glide.with(itemView)
                    .load(artilce.urlToImage)
                    .into(imgPoster)

                itemView.setOnClickListener { onItemClickListener.onItem(artilce) }
            }
        }
    }

    interface OnItemClickListener {
        fun onItem(article: Article)
    }
}