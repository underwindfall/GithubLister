package com.qifan.githublister.core.di.modules

import com.google.gson.GsonBuilder
import com.qifan.githublister.BuildConfig
import com.qifan.githublister.core.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Qifan on 2019-08-11.
 */

val netWorkModules = module {

    single { GsonBuilder().create() }

    single {
        OkHttpClient.Builder().apply {
            addInterceptor(HttpLoggingInterceptor().apply {
                level = if (BuildConfig.DEBUG) {
                    HttpLoggingInterceptor.Level.BODY
                } else {
                    HttpLoggingInterceptor.Level.NONE
                }
            })
        }.build()
    }

    single<Retrofit> {
        Retrofit.Builder()
            .client(get())
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(get()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }
}
