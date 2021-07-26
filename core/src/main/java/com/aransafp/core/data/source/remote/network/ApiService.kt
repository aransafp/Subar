package com.aransafp.core.data.source.remote.network

import com.aransafp.core.data.source.remote.response.ListArticleResponse
import com.aransafp.core.utils.Const
import retrofit2.http.GET

interface ApiService {

    @GET("top-headlines?country=id&apiKey=${Const.appKey}")
    suspend fun getListArticle(
    ): ListArticleResponse
}