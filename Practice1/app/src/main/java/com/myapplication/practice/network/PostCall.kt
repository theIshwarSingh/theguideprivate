package com.myapplication.practice.network

import android.util.Log
import androidx.lifecycle.LiveData
import com.google.gson.GsonBuilder
import com.myapplication.practice.dataclass.PostDataClass
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class PostCall {

    private val base_URL = "https://jsonplaceholder.typicode.com"

    private lateinit var postAPI: PostAPI
    val gson = GsonBuilder().setLenient().create()

    init {
        val retrofit = Retrofit
            .Builder()
            .baseUrl(base_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson)).build()
        postAPI = retrofit.create(PostAPI::class.java)
    }

    fun getPosts(): Observable<Response<List<PostDataClass>>> {
        return postAPI.getAllPosts().doOnNext {
            it.body()
        }
    }
}