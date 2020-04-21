package com.myapplication.practice.network

import com.myapplication.practice.dataclass.PostDataClass
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET

interface PostAPI {
    @GET("/posts")
    fun getAllPosts():Observable<Response<List<PostDataClass>>>
}