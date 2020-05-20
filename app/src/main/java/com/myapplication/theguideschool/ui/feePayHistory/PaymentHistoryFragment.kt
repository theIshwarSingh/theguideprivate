package com.myapplication.theguideschool.ui.feePayHistory

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager

import com.myapplication.theguideschool.R
import com.myapplication.theguideschool.ui.feePayHistory.adapters.AdapterPaymentHistory
import com.myapplication.theguideschool.datalayer.AppDB
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_payment__history.view.*
import java.lang.Exception

class PaymentHistoryFragment : Fragment() {

    private var compositeDisposable = CompositeDisposable()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_payment__history, container, false)

        compositeDisposable.add(
            Observable.create<Unit> {
                try {
                    val db = AppDB.getAppDataBase(this.requireContext())
                    val paymentHistorydetails = db?.paymenthistory()
                    val details = paymentHistorydetails?.show_Payment_details()
                    view.recyclerView_pay_history.apply {
                        layoutManager = LinearLayoutManager(context)
                        // Access the RecyclerView Adapter and load the data into it
                        adapter = details?.let { it1 ->
                            AdapterPaymentHistory(it1, context)
                        }
                        it.onNext(Unit)
                    }
                } catch (e: Exception) {
                    it.onError(e)
                    Log.e("DB", e.message.toString())
                }
                it.onComplete()
            }.subscribeOn(Schedulers.io())
                .subscribe()
        )
        return view
    }
}
