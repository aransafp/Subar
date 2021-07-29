package com.aransafp.core.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "article")
data class ArticleEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "publishAt")
    val publishedAt: String?,

    @ColumnInfo(name = "author")
    val author: String?,

    @ColumnInfo(name = "image")
    val urlToImage: String?,

    @ColumnInfo(name = "description")
    val description: String?,

    @ColumnInfo(name = "content")
    val content: String?,

    @ColumnInfo(name = "url")
    val url: String?,

    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = true
)