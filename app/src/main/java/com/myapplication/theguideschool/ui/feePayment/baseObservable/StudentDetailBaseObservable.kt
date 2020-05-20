package com.myapplication.theguideschool.ui.feePayment.baseObservable

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.myapplication.theguideschool.R
import com.myapplication.theguideschool.customViews.LayoutBinding

class StudentDetailBaseObservable(
    @Bindable val studentID: String,
    @Bindable val studentName: String
) : BaseObservable(), LayoutBinding {

    override fun getLayoutId(): Int {
        return R.layout.item_student_details
    }
}
