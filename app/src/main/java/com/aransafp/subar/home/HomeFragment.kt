package com.aransafp.subar.home

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.aransafp.core.data.Resource
import com.aransafp.subar.databinding.FragmentHomeBinding
import com.aransafp.subar.detail.DetailActivity
import com.aransafp.subar.ui.ArticleAdapter
import org.koin.android.viewmodel.ext.android.viewModel


class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModel()

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {

            val articleAdapter = ArticleAdapter()

            homeViewModel.articles.observe(viewLifecycleOwner, { articles ->
                if (articles != null) {
                    when (articles) {
                        is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                        is Resource.Success -> {
                            binding.progressBar.visibility = View.GONE
                            articleAdapter.setArticles(articles.data)
                            articleAdapter.notifyDataSetChanged()
                        }
                        is Resource.Error -> {
                            binding.progressBar.visibility = View.GONE
                        }
                    }
                }
            })

            with(binding.rvArticle) {
                layoutManager = LinearLayoutManager(context)
                adapter = articleAdapter
            }

            articleAdapter.setOnItemClickCallback(object : ArticleAdapter.OnItemClickListener {
                override fun onItem(articleId: Int) {

                    val intent = Intent(requireActivity(), DetailActivity::class.java)
                    intent.putExtra(DetailActivity.EXTRA_ARTICLE_ID, articleId)
                    startActivity(intent)

                }
            })
        }
    }
}