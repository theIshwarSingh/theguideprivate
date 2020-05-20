package com.myapplication.theguideschool.ui.feePayment.baseObservable

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable

class PayFeeBaseObservable(
    @Bindable val feeAmount: String,
    @Bindable val FeeMonth: String,
    @Bindable val studentName: String,
    @Bindable val studentClass: String,
    @Bindable val studentSection: String,
    @Bindable val annualCharges: String,
    @Bindable val educationCharges: String,
    @Bindable val computerCharges: String,
    @Bindable val musicCharges: String,
    @Bindable val smartClassCharges:String,
    @Bindable val convCharges:String,
    @Bindable val facilityCharges:String,
    @Bindable val otherCharges:String,
    @Bindable val discount:String
) : BaseObservable()

