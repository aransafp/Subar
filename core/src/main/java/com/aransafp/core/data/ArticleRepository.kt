package com.aransafp.core.data

import com.aransafp.core.data.source.local.LocalDataSource
import com.aransafp.core.data.source.remote.RemoteDataSource
import com.aransafp.core.data.source.remote.network.ApiResponse
import com.aransafp.core.data.source.remote.response.ArticleResponse
import com.aransafp.core.domain.model.Article
import com.aransafp.core.domain.repository.IArticleRepository
import com.aransafp.core.utils.AppExecutors
import com.aransafp.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ArticleRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors

) : IArticleRepository {
    override fun getAllArticle(): Flow<Resource<List<Article>>> =
        object : NetworkBoundResource<List<Article>, List<ArticleResponse>>() {
            override fun loadFromDB(): Flow<List<Article>> {
                return localDataSource.getAllArticle().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Article>?): Boolean = data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<ArticleResponse>>> =
                remoteDataSource.getAllArticle()

            override suspend fun saveCallResult(data: List<ArticleResponse>) {
                val articleList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertArticle(articleList)
            }

        }.asFlow()

    override fun getFavoriteArticle(): Flow<List<Article>> {
        return localDataSource.getFavoriteArticle().map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun setFavoriteArticle(article: Article, state: Boolean) {
        val articleEntity = DataMapper.mapDomainToEntity(article)
        appExecutors.diskIO().execute {
            localDataSource.setFavoriteArticle(articleEntity, state)
        }
    }

}