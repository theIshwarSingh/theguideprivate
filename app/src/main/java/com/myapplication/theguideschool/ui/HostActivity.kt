package com.myapplication.theguideschool.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.myapplication.theguideschool.R
import com.myapplication.theguideschool.ui.notifications.NotificationFragment
import com.myapplication.theguideschool.ui.feePayHistory.PayHistoryFragment
import kotlinx.android.synthetic.main.activity_fee_payment_actiivity2.*

class HostActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_host)
        val int = intent.extras
        Log.d("bundle", int?.toString())
        fragmentcall(int!!.getInt("fragment"))

    }

    private fun fragmentcall(fragment: Int) {
        when (fragment) {
            0 -> {
                setupToolBar("Payment History")
                supportFragmentManager.beginTransaction()
                    .replace(R.id.host_frame,
                        PayHistoryFragment()
                    ).commit()
            }
            1 -> {
                setupToolBar("Notifications")
                supportFragmentManager.beginTransaction()
                    .replace(R.id.host_frame,
                        NotificationFragment()
                    ).commit()

            }
        }
    }

    private fun setupToolBar(title: String) {
        setSupportActionBar(toolbar)
        supportActionBar?.let {
            it.title = title
            it.elevation = 4.0F
        }
    }

}

