package com.valentinerutto.datacacheroom.data.remote.api

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.valentinerutto.datacacheroom.utils.Constants
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

object RetrofitClient {

    @OptIn(ExperimentalSerializationApi::class)
    fun createRetrofit(baseUrl: String, okHttpClient: OkHttpClient): Retrofit {

        val contentType = "application/json".toMediaType()
        val converterFactory = Json.asConverterFactory(contentType)

        return Retrofit.Builder().baseUrl(baseUrl).client(okHttpClient)
            .addConverterFactory(converterFactory).build()
    }

    fun createOkClient(): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(getLoggingInterceptor()).addInterceptor(
                apiKeyInterceptor(
                    Constants.API_KEY
                )
            ).build()
    }

    private fun getLoggingInterceptor(): Interceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        return httpLoggingInterceptor
    }
}

fun apiKeyInterceptor(apiKey: String): Interceptor {
    return Interceptor { chain ->
        intercept(chain, apiKey)

    }

}

fun intercept(chain: Interceptor.Chain, apiKey: String): okhttp3.Response {
    val originalRequest: Request = chain.request()
    val requestBuilder = originalRequest.newBuilder().header("X-Api-Key", apiKey)
    val request = requestBuilder.build()
    return chain.proceed(request)
}



