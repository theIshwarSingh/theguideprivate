package com.myapplication.theguideschool.datalayer

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PaymentHistory(
        @PrimaryKey(autoGenerate = true) val nid:Int,
        @ColumnInfo(name = "paymentID") val payment_id: String,
        @ColumnInfo(name = "status") var status: String,
        @ColumnInfo(name = "txn_id") val txnid: String,
        @ColumnInfo(name = "first_name") val first_name: String,
        @ColumnInfo(name = "class_name") val class_name: String,
        @ColumnInfo(name = "section_name") val section_name: String,
        @ColumnInfo(name = "annualCharge") val annualCharge: Double,
        @ColumnInfo(name = "educationCharges") val educationCharges: Double,
        @ColumnInfo(name = "computerCharges") val computerCharges: Double,
        @ColumnInfo(name = "musicCharges") val musicCharges: Double,
        @ColumnInfo(name = "smartClassCharges") val smartClassCharges: Double,
        @ColumnInfo(name = "convCharges") val convCharges: Double,
        @ColumnInfo(name = "facilityCharges") val facilityCharges: Double,
        @ColumnInfo(name = "otherCharges") val otherCharges: Double,
        @ColumnInfo(name = "discount") val discount: Double,
        @ColumnInfo(name = "total_amount") val amount: Double,
        @ColumnInfo(name = "due_month") val due_month: String,
        @ColumnInfo(name = "paidOn_month") val paidOn_month: String
)