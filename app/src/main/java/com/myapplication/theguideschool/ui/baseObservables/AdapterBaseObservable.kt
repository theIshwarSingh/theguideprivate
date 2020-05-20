package com.myapplication.theguideschool.ui.baseObservables

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.myapplication.theguideschool.customViews.MultiTypeDataBoundAdapter

class AdapterBaseObservable(listener: MultiTypeDataBoundAdapter.ActionCallback?) :BaseObservable() {

    @Bindable
    val adapter = MultiTypeDataBoundAdapter(listener)

}