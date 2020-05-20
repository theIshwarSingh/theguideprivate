package com.myapplication.theguideschool.ui.feePayment.baseObservable

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.myapplication.theguideschool.customViews.MultiTypeDataBoundAdapter

class FeePaymentBaseObservable(listener: MultiTypeDataBoundAdapter.ActionCallback?) : BaseObservable() {
    @Bindable
    val adapter = MultiTypeDataBoundAdapter(listener)

}