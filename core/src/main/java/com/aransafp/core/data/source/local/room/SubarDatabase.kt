package com.aransafp.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.aransafp.core.data.source.local.entity.ArticleEntity

@Database(entities = [ArticleEntity::class], version = 1, exportSchema = false)
abstract class SubarDatabase : RoomDatabase() {

    abstract fun subarDao(): SubarDao

}