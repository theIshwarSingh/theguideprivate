package com.myapplication.theguideschool

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.myapplication.theguideschool.ui.HostActivity
import com.myapplication.theguideschool.ui.feePayment.FeeActivity3
import com.myapplication.theguideschool.ui.feePayment.FeePaymentActiivity2
import com.myapplication.theguideschool.viewModel.SchoolClassViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
       init()
        viewModelSetup()

    }

    private fun init() {

        notify.setOnClickListener {
            val intent = Intent(this, HostActivity::class.java)
            intent.putExtra("fragment", 1)
            startActivity(intent)
        }

        fee.setOnClickListener {
            val intent = Intent(this, FeePaymentActiivity2::class.java)
            startActivity(intent)
        }
        pay_history.setOnClickListener {
            val intent = Intent(this, HostActivity::class.java)
            intent.putExtra("fragment", 0)
            startActivity(intent)
        }
        regis.setOnClickListener {
            val intent = Intent(this, FeeActivity3::class.java)
            startActivity(intent)
        }



    }

    override fun onBackPressed() {
        super.onBackPressed()
        onDestroy()
    }

    fun viewModelSetup() {
        val theguideViewModel = ViewModelProviders.of(this).get(SchoolClassViewModel::class.java)
        theguideViewModel.data.observe(this, Observer {
            theguideViewModel.postData = it.toString()
        })

    }

}