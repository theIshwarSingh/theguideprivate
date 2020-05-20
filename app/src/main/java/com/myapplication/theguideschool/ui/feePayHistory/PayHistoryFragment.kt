package com.myapplication.theguideschool.ui.feePayHistory

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.myapplication.theguideschool.R
import com.myapplication.theguideschool.customViews.MultiTypeDataBoundAdapter
import com.myapplication.theguideschool.databinding.FragmentPayHistoryBinding
import com.myapplication.theguideschool.datalayer.AppDB
import com.myapplication.theguideschool.ui.baseObservables.AdapterBaseObservable
import com.myapplication.theguideschool.ui.baseObservables.callback.AdapterCallBack
import com.myapplication.theguideschool.ui.feePayHistory.baseObservable.PaymentHistoryBaseObservable
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.lang.Exception


class PayHistoryFragment : Fragment() {

    private var compositeDisposable = CompositeDisposable()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentPayHistoryBinding>(
            inflater,
            R.layout.fragment_pay_history,
            container,
            false
        )
        binding.data = AdapterBaseObservable(object : AdapterCallBack {
            override fun onItemClick() {
                Toast.makeText(context, "Click", Toast.LENGTH_SHORT).show()
            }

        })
            .apply {
                showPaymentHistory(adapter)
            }
        return binding.root
    }

    private fun showPaymentHistory(adapter: MultiTypeDataBoundAdapter) {
        compositeDisposable.add(
            Observable.create<Unit> {
                try {
                    val db = AppDB.getAppDataBase(this.requireContext())
                    val paymentHistoryDB = db?.paymenthistory()
                    val showPayments = paymentHistoryDB?.show_Payment_details()
                    showPayments?.map {
                        adapter.addItem(
                            PaymentHistoryBaseObservable(
                                it.status,
                                it.first_name,
                                "\u20B9  ${it.amount}",
                                paidOnDate = it.paidOn_month
                            )
                        )
                    }
                    it.onNext(Unit)
                } catch (e: Exception) {
                    it.onError(e)
                    Log.e("DB", e.message.toString())
                }
                it.onComplete()
            }.subscribeOn(Schedulers.io())
                .subscribe()
        )
    }
}
