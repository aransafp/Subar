package com.aransafp.subar.favorite.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.aransafp.favorite.databinding.ActivityFavoriteBinding
import com.aransafp.subar.favorite.di.favoriteModule
import com.aransafp.subar.ui.ArticleAdapter
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
            favoriteAdapter.notifyDataSetChanged()
        })

        with(binding.rvArticle) {
            layoutManager = LinearLayoutManager(this@FavoriteActivity)
            adapter = favoriteAdapter
        }
    }
}