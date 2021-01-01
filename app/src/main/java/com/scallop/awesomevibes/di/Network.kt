package com.scallop.awesomevibes.di

import android.content.Context
import com.scallop.awesomevibes.BuildConfig
import com.scallop.awesomevibes.common.Properties
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

fun createNetworkClient(baseUrl: String, context: Context) =
    retrofitClient(baseUrl, httpClient(context))

private fun httpClient(context: Context): OkHttpClient {
    val myCache = Cache(context.cacheDir, Properties.CACHE_SIZE_BYTES)

    val httpLoggingInterceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT)
    val clientBuilder = OkHttpClient.Builder()
    if (BuildConfig.DEBUG) {
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        clientBuilder.addInterceptor(httpLoggingInterceptor)
    }
    clientBuilder.cache(myCache)
    clientBuilder.addInterceptor { chain ->
        var request = chain.request()
        request = request.newBuilder()
            .header("Cache-Control", "public, max-age=${Properties.MAX_SECONDS_VALID_CACHE}")
            .build()
        chain.proceed(request)
    }
    clientBuilder.readTimeout(Properties.NETWORK_CLIENT_TIMEOUT, TimeUnit.SECONDS)
    clientBuilder.writeTimeout(Properties.NETWORK_CLIENT_TIMEOUT, TimeUnit.SECONDS)
    return clientBuilder.build()
}

private fun getMoshi() = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private fun retrofitClient(baseUrl: String, httpClient: OkHttpClient): Retrofit =
    Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(httpClient)
        .addConverterFactory(MoshiConverterFactory.create(getMoshi()))
        .build()