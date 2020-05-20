package com.myapplication.theguideschool.model

import com.google.gson.annotations.SerializedName

data class ClassName (
    @SerializedName("classID") var classID:Int,
    @SerializedName("className") var className:String
)