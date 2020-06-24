package com.myapplication.newapi.network

import com.myapplication.newapi.model.BreedImagesDataClass
import com.myapplication.newapi.model.BreedsDataClass
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface CatAPI {

    @GET("/v1/breeds")
    suspend fun getBreeds(
        @Header("api-key") apikey: String
    ): Response<List<BreedsDataClass>>


    @GET("v1/images/search")
    suspend fun getBreedImages(
        @Header("api-key") apikey: String
    ): Response<List<BreedImagesDataClass>>
}