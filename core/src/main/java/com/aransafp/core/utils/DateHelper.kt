package com.aransafp.core.utils

object DateHelper {

    fun dateFormat(date: String): String {
        val rawDate = date.split("T")

        return rawDate[0]
    }
}