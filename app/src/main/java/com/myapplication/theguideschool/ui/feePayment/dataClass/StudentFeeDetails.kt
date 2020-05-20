package com.myapplication.theguideschool.ui.feePayment.dataClass
import com.google.gson.annotations.SerializedName

data class StudentFeeDetails(
    @SerializedName("annualCharges") var annualCharges: Double,
    @SerializedName("classID") var classID: String,
    @SerializedName("className") var className: String,
    @SerializedName("computerCharges") var computerCharges: Double,
    @SerializedName("convCharges") var convCharges: Double,
    @SerializedName("discount") var discount: Double,
    @SerializedName("educationCharges") var educationCharges: Double,
    @SerializedName("facilityCharges") var facilityCharges: Double,
    @SerializedName("feeID") var feeID: String,
    @SerializedName("feeMonth") var feeMonth: String,
    @SerializedName("musicCharges") var musicCharges: Double,
    @SerializedName("otherCharges") var otherCharges: Double,
    @SerializedName("sectionID") var sectionID: String,
    @SerializedName("sectionName") var sectionName: String,
    @SerializedName("smartClassCharges") var smartClassCharges: Double,
    @SerializedName("studentEmail") var studentEmail: String,
    @SerializedName("studentFatherName") var studentFatherName: String,
    @SerializedName("studentID") var studentID: String,
    @SerializedName("studentMobileNumber") var studentMobileNumber: String,
    @SerializedName("studentMotherName") var studentMotherName: String,
    @SerializedName("studentName") var studentName: String,
    @SerializedName("yearID") var yearID: String
)