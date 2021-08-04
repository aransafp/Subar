package com.aransafp.subar.ui.detail

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

    companion object {
        const val EXTRA_ARTICLE_ID = "extra_article_id"
    }

    private val detailViewModel: DetailViewModel by viewModel()
    private lateinit var binding: ActivityDetailBinding
    private lateinit var articleData: Article
    private var statusFavorite: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val articleId = intent.getIntExtra(EXTRA_ARTICLE_ID, 0)

        detailViewModel.getArticle(articleId).observe(this, { article ->
            loadWebView(article.url)

            articleData = article

            statusFavorite = article.isFavorite
            setStatusFavorite(statusFavorite)

        })

        binding.fab.setOnClickListener {
            statusFavorite = !statusFavorite
            detailViewModel.setFavoriteArticle(articleData, statusFavorite)

            if (statusFavorite) {
                showToast("add to favorite")
            } else {
                showToast("remove from favorite")
            }
            setStatusFavorite(statusFavorite)
        }

    }

    private fun loadWebView(articleUrl: String?) {
        with(binding.webview) {
            webViewClient = WebViewClient()
            loadUrl(articleUrl as String)
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

}