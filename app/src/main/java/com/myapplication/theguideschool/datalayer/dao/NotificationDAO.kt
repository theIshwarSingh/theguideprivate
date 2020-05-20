package com.myapplication.theguideschool.datalayer.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.myapplication.theguideschool.datalayer.NotificationDB


@Dao
interface NotificationDAO {
    @Insert
    fun insertall(vararg notificationDB: NotificationDB)

    @Query("select * from NotificationDB ORDER BY id DESC")
    fun getNotifications():List<NotificationDB>

}