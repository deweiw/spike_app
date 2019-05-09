package com.dandv.data.di

import android.app.Application
import com.dandv.data.common.NetworkClient
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class ClientModule {

    @Singleton
    @Provides
    internal fun provideHttpCache(application: Application): Cache {
        val cacheSize: Long = 10 * 1024 * 1024
        return Cache(application.cacheDir, cacheSize)
    }

    @Singleton
    @Provides
    internal fun provideInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

    @Singleton
    @Provides
    internal fun provideOkhttpClient(cache: Cache, interceptor: HttpLoggingInterceptor): OkHttpClient {
        val client = OkHttpClient.Builder()
        client.cache(cache).addInterceptor(interceptor)
        return client.build()
    }

    @Singleton
    @Provides
    internal fun provideNetworkClient(okHttpClient: OkHttpClient): NetworkClient {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl("https://gist.githubusercontent.com/deweiw/")
            .client(okHttpClient)
            .build()
            .create(NetworkClient::class.java)
    }
}