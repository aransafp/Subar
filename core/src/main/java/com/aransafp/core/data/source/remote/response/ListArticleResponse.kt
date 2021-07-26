package com.aransafp.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ListArticleResponse(

	@field:SerializedName("articles")
	val articles: List<ArticleResponse>,

)

