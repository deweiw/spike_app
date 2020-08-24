package com.dandv.data.di

import android.app.Application
import com.dandv.data.common.NetworkClient
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val clientModule = module {
    single { provideHttpCache(androidApplication()) }
    single { provideInterceptor() }
    single { provideOkhttpClient(cache = get(), interceptor = get()) }
    single { provideNetworkClient(okHttpClient = get()) }
}

private fun provideHttpCache(application: Application): Cache {
    val cacheSize: Long = 10 * 1024 * 1024
    return Cache(application.cacheDir, cacheSize)
}

private fun provideInterceptor(): HttpLoggingInterceptor {
    val interceptor = HttpLoggingInterceptor()
    interceptor.level = HttpLoggingInterceptor.Level.BODY
    return interceptor
}

private fun provideOkhttpClient(cache: Cache, interceptor: HttpLoggingInterceptor): OkHttpClient {
    val client = OkHttpClient.Builder()
    client.cache(cache).addInterceptor(interceptor)
    return client.build()
}

private fun provideNetworkClient(okHttpClient: OkHttpClient): NetworkClient {
    return Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .baseUrl("https://gist.githubusercontent.com/deweiw/")
        .client(okHttpClient)
        .build()
        .create(NetworkClient::class.java)
}