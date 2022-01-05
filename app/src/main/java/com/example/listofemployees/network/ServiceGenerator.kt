package com.example.listofemployees.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object ServiceGenerator {
    private const val BASE_URL = "https://dummy.restapiexample.com/api/v1/"
    private val logging = HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.BODY)
    private val httpClient = OkHttpClient.Builder().addInterceptor(logging)
    private val retrofitBuilder = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(httpClient.build())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
    private val retrofit = retrofitBuilder.build()
    val apiService: ApiService = retrofit.create(
        ApiService::class.java
    )
}