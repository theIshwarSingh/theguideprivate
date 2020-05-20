package com.myapplication.theguideschool.datalayer.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.myapplication.theguideschool.datalayer.PaymentHistory


@Dao
interface PaymentDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert_payment_details(payHistory: PaymentHistory)

    @Query("select * from PaymentHistory ORDER BY nid DESC")
    fun show_Payment_details():List<PaymentHistory>
}
