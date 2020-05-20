package com.myapplication.theguideschool.ui.feePayment.dataClass

import com.google.gson.annotations.SerializedName

data class StudentsDetails(
    @SerializedName("classID")
    var classID: String,
    @SerializedName("sectionID")
    var sectionID: String,
    @SerializedName("studentEmail")
    var studentEmail: String,
    @SerializedName("studentFatherName")
    var studentFatherName: String,
    @SerializedName("studentID")
    var studentID: String,
    @SerializedName("studentMobileNumber")
    var studentMobileNumber: String,
    @SerializedName("studentMotherName")
    var studentMotherName: String,
    @SerializedName("studentName")
    var studentName: String,
    @SerializedName("yearID")
    var yearID: String
)