package com.myapplication.theguideschool.ui.feePayHistory.baseObservable

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.myapplication.theguideschool.R
import com.myapplication.theguideschool.customViews.LayoutBinding

class PaymentHistoryBaseObservable (
    @Bindable val paymentStatus: String,
    @Bindable val studentName: String,
    @Bindable val feeAmount:String,
    @Bindable val paidOnDate:String
):BaseObservable(), LayoutBinding{
    override fun getLayoutId(): Int {
        return R.layout.item_payment_history
    }
}