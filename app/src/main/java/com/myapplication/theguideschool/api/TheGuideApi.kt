package com.myapplication.theguideschool.api

import com.myapplication.theguideschool.model.ClassName
import com.myapplication.theguideschool.ui.feePayment.dataClass.StudentFeeDetails
import com.myapplication.theguideschool.ui.feePayment.dataClass.StudentsDetails
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TheGuideApi {

    @GET("/index.php")
    fun getClasses(
        @Query("classID") classid: Int
    ): Observable<Response<List<ClassName>>>

    @GET("schoolapp/getStudentDetails.php")
    fun getStudent(
        @Query("studentID") studentID: Int
    ): Observable<Response<List<StudentsDetails>>>

    @GET("schoolapp/getStudentFee.php")
    fun getFeeDetails(
        @Query("studentID") studentID: Int,
        @Query("feeMonth") feeMonth: String
    ): Observable<Response<List<StudentFeeDetails>>>
}