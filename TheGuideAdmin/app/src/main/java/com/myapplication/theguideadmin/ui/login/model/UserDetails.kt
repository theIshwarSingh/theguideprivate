package com.myapplication.theguideadmin.ui.login.model


import com.google.gson.annotations.SerializedName

data class UserDetails(
    @SerializedName("id")
    var id: String,
    @SerializedName("level")
    var level: String,
    @SerializedName("username")
    var username: String,
    @SerializedName("userpassword")
    var userpassword: String
)