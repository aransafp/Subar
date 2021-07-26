package com.aransafp.subar.detail

import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.aransafp.core.domain.model.Article
import com.aransafp.subar.R
import com.aransafp.subar.databinding.ActivityDetailBinding
import org.koin.android.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {

    private val detailViewModel: DetailViewModel by viewModel()
    private lateinit var binding: ActivityDetailBinding

    companion object {

        const val EXTRA_ARTICLE = "extra_article"

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val article = intent.getParcelableExtra<Article>(EXTRA_ARTICLE) as Article

        loadWebView(article)

        var statusFavorite = article.isFavorite
        setStatusFavorite(statusFavorite)
        binding.fab.setOnClickListener {

            statusFavorite = !statusFavorite
            detailViewModel.setFavoriteArticle(article, statusFavorite)

            if (statusFavorite) showToast("add to favorite") else showToast("remove from favorite")
            setStatusFavorite(statusFavorite)
        }
    }

    private fun loadWebView(article: Article) {
        val url = article.url as String

        with(binding.webview) {

            webViewClient = WebViewClient()
        }
        binding.webview.loadUrl(url)

    }

    inner class WebViewClient : android.webkit.WebViewClient() {
        override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
            view.loadUrl(url)
            return false
        }

        override fun onPageFinished(view: WebView, url: String) {
            super.onPageFinished(view, url)
            binding.progressBar.visibility = View.GONE
        }
    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        if (statusFavorite) {
            binding.fab.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_menu_favorite_24
                )
            )
        } else {
            binding.fab.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_menu_favorite_border_24
                )
            )
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}