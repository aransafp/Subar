package com.aransafp.subar.favorit.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.aransafp.core.domain.model.Article
import com.aransafp.subar.detail.DetailActivity
import com.aransafp.subar.favorit.databinding.ActivityFavoriteBinding
import com.aransafp.subar.favorit.di.favoriteModule
import com.aransafp.subar.ui.ArticleAdapter
import kotlinx.android.synthetic.main.activity_favorite.*
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavoriteActivity : AppCompatActivity() {

    private val favoriteViewModel: FavoriteViewModel by viewModel()
    private lateinit var binding: ActivityFavoriteBinding

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadKoinModules(favoriteModule)

        val favoriteAdapter = ArticleAdapter()

        favoriteViewModel.articles.observe(this, { articles ->
            favoriteAdapter.setArticles(articles)
            favoriteAdapter.notifyDataSetChanged()
            tv_message.visibility = if (articles == null) View.VISIBLE else View.GONE
        })

        with(binding.rvArticle) {
            layoutManager = LinearLayoutManager(this@FavoriteActivity)
            adapter = favoriteAdapter
        }

        favoriteAdapter.setOnItemClickCallback(object : ArticleAdapter.OnItemClickListener {
            override fun onItem(articleId: Int) {
                val intent = Intent(this@FavoriteActivity, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_ARTICLE_ID, articleId)
                startActivity(intent)
            }
        })

        supportActionBar?.title = "Favorite"
    }
}