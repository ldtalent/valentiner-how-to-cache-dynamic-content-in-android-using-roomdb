package com.valentinerutto.datacacheroom.di

import com.valentinerutto.datacacheroom.App
import com.valentinerutto.datacacheroom.data.remote.RetrofitClient.createOkClient
import com.valentinerutto.datacacheroom.data.remote.RetrofitClient.createRetrofit
import com.valentinerutto.datacacheroom.data.remote.api.ApiService
import com.valentinerutto.datacacheroom.utils.Constants
import org.koin.dsl.module
import retrofit2.Retrofit

val appModules = module {

    single { App.INSTANCE }
    single<ApiService> { (get() as Retrofit).create(ApiService::class.java) }
    single { createOkClient() }

    single {
        createRetrofit(
            baseUrl = Constants.BASE_URL,
            get()
        )
    }
}