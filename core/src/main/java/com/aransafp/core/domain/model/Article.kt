package com.aransafp.core.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Article(

    val id: Int,

    val publishedAt: String?,

    val author: String?,

    val urlToImage: String?,

    val description: String?,

    val title: String,

    val content: String?,

    val url: String?,

    var isFavorite: Boolean

) : Parcelable