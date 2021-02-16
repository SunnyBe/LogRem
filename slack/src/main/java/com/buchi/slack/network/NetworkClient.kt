package com.buchi.slack.network

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object NetworkClient {

    fun apiService(): SlackApi {
        val retrofitBuilder = Retrofit.Builder()
            .baseUrl("https://hooks.slack.com/services/")
            .client(slackHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofitBuilder.create(SlackApi::class.java)
    }

    private fun slackHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val httpHeader = Interceptor { chain ->
            val requestBuilder: Request.Builder = chain.request().newBuilder()
            requestBuilder.header("Content-Type", "application/json")
            requestBuilder.header("Accept", "application/json")
            chain.proceed(requestBuilder.build())
        }

        return OkHttpClient.Builder()
            .addInterceptor(httpHeader)
            .addInterceptor(loggingInterceptor)
            .build()
    }

}