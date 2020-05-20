package com.myapplication.theguideschool.model.Payumoney

import android.app.Application
import com.myapplication.theguideschool.model.AppEnvironment



class BaseApplication : Application(){

    var appEnvironment:AppEnvironment? = null

    override fun onCreate() {
        super.onCreate()
        appEnvironment = AppEnvironment.SANDBOX
    }


    fun get_AppEnvironment(): AppEnvironment {
        return appEnvironment!!
    }

    fun set_AppEnvironment(appEnvironment: AppEnvironment) {
        this.appEnvironment = appEnvironment
    }
}