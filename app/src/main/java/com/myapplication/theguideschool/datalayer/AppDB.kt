package com.myapplication.theguideschool.datalayer

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.myapplication.theguideschool.datalayer.dao.NotificationDAO
import com.myapplication.theguideschool.datalayer.dao.PaymentDao
import com.myapplication.theguideschool.datalayer.dao.StudentDetailsDao


@Database(
    entities = [PaymentHistory::class, NotificationDB::class, StudentDetailsDB::class],
    version = 5
)
abstract class AppDB : RoomDatabase() {
    abstract fun notificationDB(): NotificationDAO
    abstract fun paymenthistory(): PaymentDao
    abstract fun studentDetailsDB(): StudentDetailsDao

    companion object {
        var INSTANCE: AppDB? = null

        fun getAppDataBase(context: Context): AppDB? {
            if (INSTANCE == null) {
                synchronized(AppDB::class) {
                    INSTANCE =
                        Room.databaseBuilder(context.applicationContext, AppDB::class.java, "AppDB")
                            .build()
                }
            }
            return INSTANCE
        }

        fun destroyDataBase() {
            INSTANCE = null
        }
    }
}