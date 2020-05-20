package com.myapplication.theguideschool.ui.notifications

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.myapplication.theguideschool.R
import com.myapplication.theguideschool.customViews.LayoutBinding

class NotificationBaseObservable(
    @Bindable val notificationtitle: String,
    @Bindable val notificationBody: String,
    @Bindable val notifaictionTimeStamp: String
) : BaseObservable(), LayoutBinding {

    override fun getLayoutId(): Int {
        return R.layout.item_notification
    }

}