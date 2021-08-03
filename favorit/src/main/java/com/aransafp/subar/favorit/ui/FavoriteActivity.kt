package com.aransafp.subar.favorit.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.aransafp.subar.favorit.databinding.ActivityFavoriteBinding
import com.aransafp.subar.favorit.di.favoriteModule
import com.aransafp.subar.ui.ArticleAdapter
import com.aransafp.subar.ui.detail.DetailActivity
import kotlinx.android.synthetic.main.activity_favorite.*
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavoriteActivity : AppCompatActivity() {

    private val favoriteViewModel: FavoriteViewModel by viewModel()
    private lateinit var binding: ActivityFavoriteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadKoinModules(favoriteModule)

        val favoriteAdapter = ArticleAdapter()
        favoriteViewModel.articles.observe(this, { articles ->

            favoriteAdapter.setArticles(articles)
            binding.viewEmpty.root.visibility =
                if (articles.isNotEmpty()) View.GONE else View.VISIBLE

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