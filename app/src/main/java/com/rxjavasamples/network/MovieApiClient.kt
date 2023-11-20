package com.rxjavasamples.network

import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object MovieApiClient {

    const val BASE_URL = "https://api.themoviedb.org/3/"

    val apiClient: MovieApiInterface by lazy {

        var retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()

        return@lazy retrofit.create(MovieApiInterface::class.java)
    }
}