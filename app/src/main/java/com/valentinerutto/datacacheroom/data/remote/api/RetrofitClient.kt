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
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private val contentType = "application/json".toMediaType()

    @OptIn(ExperimentalSerializationApi::class)
    fun createRetrofit(baseUrl: String, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(Json.asConverterFactory(contentType))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun createOkClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(getLoggingInterceptor())
            .addInterceptor(
                com.valentinerutto.datacacheroom.data.remote.api.apiKeyInterceptor(
                    Constants.API_KEY
                )
            )
            .build()
    }

    private fun getLoggingInterceptor(): Interceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        return httpLoggingInterceptor
    }
}

   fun  apiKeyInterceptor(apiKey:String): Interceptor {
return Interceptor{chain->
intercept(chain,apiKey)
      // val httpClient = OkHttpClient.Builder()
      // httpClient.addInterceptor { chain ->
//           val original = chain.request()
//
//           val requestBuilder = original.newBuilder()
//               .header("X-Api-Key", apiKey)
//           val request = requestBuilder.build()
//           chain.proceed(request)
       }

   }
//
//       httpClient.connectTimeout(30, TimeUnit.SECONDS)
//       httpClient.readTimeout(30, TimeUnit.SECONDS)
//
//       httpClient.addNetworkInterceptor(logging)
//
//       val okHttpClient = httpClient.build()


     fun intercept(chain: Interceptor.Chain,apiKey:String): okhttp3.Response {
        val originalRequest: Request = chain.request()
        val requestBuilder = originalRequest.newBuilder()
            .header("X-Api-Key", apiKey)
        val request = requestBuilder.build()
        return chain.proceed(request)
    }



