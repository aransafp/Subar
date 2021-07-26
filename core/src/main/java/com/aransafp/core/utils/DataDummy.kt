package com.aransafp.core.utils

import com.aransafp.core.domain.model.Article

object DataDummy {
    fun getListString(): List<String> {
        val listString = ArrayList<String>()

        for (i in 1..100) {
            listString.add("Favorite")
        }

        return listString
    }

    fun getArticle(): List<Article> {
        val listArticle = ArrayList<Article>()

        for (i in 1..100) {
            listArticle.add(
                Article(
                    "123",
                    "123",
                    "123",
                    "123",
                    "123",
                    "123",
                    "123",
                    true
                )
            )
        }

        return listArticle
    }
}