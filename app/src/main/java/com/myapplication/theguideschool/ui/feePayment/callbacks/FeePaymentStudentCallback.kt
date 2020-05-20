package com.myapplication.theguideschool.ui.feePayment.callbacks

import com.myapplication.theguideschool.customViews.MultiTypeDataBoundAdapter
import com.myapplication.theguideschool.ui.feePayment.baseObservable.StudentDetailBaseObservable

interface FeePaymentStudentCallback: MultiTypeDataBoundAdapter.ActionCallback {
    fun onStudentCallBack(studentDB: StudentDetailBaseObservable)
}