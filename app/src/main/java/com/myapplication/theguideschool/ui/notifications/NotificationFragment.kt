package com.myapplication.theguideschool.ui.notifications

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.myapplication.theguideschool.R
import com.myapplication.theguideschool.customViews.MultiTypeDataBoundAdapter
import com.myapplication.theguideschool.databinding.FragmentNotificationBinding
import com.myapplication.theguideschool.datalayer.AppDB
import com.myapplication.theguideschool.ui.baseObservables.AdapterBaseObservable
import com.myapplication.theguideschool.ui.baseObservables.callback.AdapterCallBack
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class NotificationFragment : Fragment() {

    private var compositeDisposable = CompositeDisposable()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentNotificationBinding>(inflater, R.layout.fragment_notification,container, false)
        binding.data = AdapterBaseObservable(object: AdapterCallBack {
            override fun onItemClick() {
                //TODO  Nothing
            }

        })
            .apply {
               showNotifications(adapter)
        }
        return binding.root
    }

    private fun showNotifications(adapter: MultiTypeDataBoundAdapter){
        compositeDisposable.add(
            Observable.create<Unit> {
                try {
                    val db = AppDB.getAppDataBase(this.requireContext())
                    val notificationDB = db?.notificationDB()
                    val showNotification = notificationDB?.getNotifications()
                        showNotification?.map {
                            adapter.addItem(
                                NotificationBaseObservable(
                                    it.title,
                                    it.message,
                                    "Post on:  ${it.date}"
                                )
                            )
                        }
                    it.onNext(Unit)
                }catch (e:Exception){
                    it.onError(e)
                    Log.e("DB", e.message.toString())
                }
                it.onComplete()
            }.subscribeOn(Schedulers.io())
                .subscribe()
        )

    }
}
