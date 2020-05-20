package com.myapplication.theguideschool.datalayer

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class StudentDetailsDB(
    @PrimaryKey(autoGenerate = true) var studentID: Int,
    @ColumnInfo(name = "studentName") var studentName: String,
    @ColumnInfo(name = "studentEmail") var studentEmail: String,
    @ColumnInfo(name = "studentPhone") var studentPhone: String
)