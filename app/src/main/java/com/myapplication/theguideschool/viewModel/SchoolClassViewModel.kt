package com.myapplication.theguideschool.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.myapplication.theguideschool.api.TheGuideCall
import com.myapplication.theguideschool.model.ClassName
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class SchoolClassViewModel : ViewModel() {

    private var disposable: Disposable? = null
    private var theGuideCall = TheGuideCall()

    var postData: String = ""
    val livePosts = MutableLiveData<List<ClassName>>()
    val data: LiveData<List<ClassName>> = livePosts


    fun getLivePosts() {
        disposable = theGuideCall.getClass()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                livePosts.value = response.body()
            }, {
                Log.e("Error", it.message)
            })
    }

    init {
        getLivePosts()
    }

}