package com.myapplication.newapi.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object catService {

    private val BASE_URL = "https://api.thecatapi.com"

    val instance:CatAPI  by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit.create(CatAPI::class.java)
    }
}