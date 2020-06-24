package com.myapplication.newapi.network

import com.myapplication.newapi.utils.apiKey

class CatRepository {

    suspend fun getBreeds() = catService.instance.getBreeds(apiKey)

    suspend fun getbreedsImages() =
        catService.instance.getBreedImages(apiKey)

}