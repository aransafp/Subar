package com.aransafp.core.di

import androidx.room.Room
import com.aransafp.core.data.ArticleRepository
import com.aransafp.core.data.source.local.LocalDataSource
import com.aransafp.core.data.source.local.room.SubarDatabase
import com.aransafp.core.data.source.remote.RemoteDataSource
import com.aransafp.core.data.source.remote.network.ApiService
import com.aransafp.core.domain.repository.IArticleRepository
import com.aransafp.core.utils.AppExecutors
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    factory {
        get<SubarDatabase>().subarDao()
    }
    single {
        val passphrase: ByteArray = SQLiteDatabase.getBytes("aransa".toCharArray())
        val factory = SupportFactory(passphrase)
        Room.databaseBuilder(
            androidContext(),
            SubarDatabase::class.java, "subar.db"
        ).fallbackToDestructiveMigration()
//            .openHelperFactory(factory)
            .build()
    }
}

val networkModule = module {

    single {
        val hostname = "newsapi.org"
        val certificatePinner = CertificatePinner.Builder()
            .add(hostname, "sha256/QZ3EWMoTShshKLrh7BRQtxMgBGc/eFICrmJ9b4o6Jh8=")
            .add(hostname, "sha256/hS5jJ4P+iQUErBkvoWBQOd1T7VOAYlOVegvv1iMzpxA=")
            .add(hostname, "sha256/UmhcQTxjIQ7hbNRvTDeFt5LId41clz5KDOcuyIP+fd4=")
            .add(hostname, "sha256/FEzVOUp4dF3gI0ZVPRJhFbSJVXR+uQmMH65xhs1glH4=")
            .build()
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .certificatePinner(certificatePinner)
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