package com.myapplication.theguideschool.datalayer

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class NotificationDB(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    @ColumnInfo(name = "title")
    var title:String,
    @ColumnInfo(name = "message")
    var message:String,
    @ColumnInfo(name = "timestamp")
    var date:String
)