package com.myapplication.theguideschool.datalayer.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.myapplication.theguideschool.datalayer.StudentDetailsDB

@Dao
interface StudentDetailsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertStudentsDetails(studentDetailsDB: StudentDetailsDB)

    @Query("select * from StudentDetailsDB ")
    fun showStudentDetails(): LiveData<List<StudentDetailsDB>>

    @Query("SELECT COUNT(*) FROM StudentDetailsDB")
    fun getStudentCount(): LiveData<Int>
}