package com.myapplication.newapi.model


import com.google.gson.annotations.SerializedName

data class BreedImagesDataClass(
    @SerializedName("breeds")
    var breeds: List<BreedsDataClass>,
    @SerializedName("height")
    var height: Int,
    @SerializedName("id")
    var id: String,
    @SerializedName("url")
    var url: String,
    @SerializedName("width")
    var width: Int
)