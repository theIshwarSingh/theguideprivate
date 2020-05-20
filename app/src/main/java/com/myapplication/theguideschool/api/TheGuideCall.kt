package com.myapplication.theguideschool.api

import com.google.gson.GsonBuilder
import com.myapplication.theguideschool.model.ClassName
import com.myapplication.theguideschool.ui.feePayment.dataClass.StudentFeeDetails
import com.myapplication.theguideschool.ui.feePayment.dataClass.StudentsDetails
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class TheGuideCall {
    private val base_URL = "http://10.0.2.2"

    private lateinit var theguideApi: TheGuideApi

    private val gson = GsonBuilder().setLenient().create()


    init {
        val retrofit = Retrofit
            .Builder()
            .baseUrl(base_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson)).build()
        theguideApi = retrofit.create(TheGuideApi::class.java)
    }

    fun getClass(): Observable<Response<List<ClassName>>> {
        return theguideApi.getClasses(2).doOnNext {
            it.body()
        }
    }

    fun getStudentDetails(studentId: Int): Observable<Response<List<StudentsDetails>>> {
        return theguideApi.getStudent(studentId).doOnNext {
            it.body()
        }
    }

    fun getStudentDetails(studentId: Int, feeMonth:String):Observable<Response<List<StudentFeeDetails>>>{
        return theguideApi.getFeeDetails(studentId,feeMonth).doOnNext {
            it.body()
        }
    }
}