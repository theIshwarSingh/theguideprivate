package com.myapplication.theguideschool.ui.baseObservables.callback

import com.myapplication.theguideschool.customViews.MultiTypeDataBoundAdapter


interface AdapterCallBack: MultiTypeDataBoundAdapter.ActionCallback{
    fun onItemClick()
}