package com.aransafp.core.di

import androidx.room.Room
import com.aransafp.core.data.ArticleRepository
import com.aransafp.core.data.source.local.LocalDataSource
import com.aransafp.core.data.source.local.room.SubarDatabase
import com.aransafp.core.data.source.remote.RemoteDataSource
import com.aransafp.core.data.source.remote.network.ApiService
import com.aransafp.core.domain.repository.IArticleRepository
import com.aransafp.core.utils.AppExecutors
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val databaseModule = module {
    factory {
        get<SubarDatabase>().subarDao()
    }
    single {
        Room.databaseBuilder(
            androidContext(),
            SubarDatabase::class.java, "subar.db"
        ).fallbackToDestructiveMigration().build()
    }
}

val networkModule = module {

    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://newsapi.org/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }

}

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    factory { AppExecutors() }
    single<IArticleRepository> {
        ArticleRepository(
            get(),
            get(),
            get()
        )
    }
}