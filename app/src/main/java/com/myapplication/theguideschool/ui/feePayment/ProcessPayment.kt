package com.myapplication.theguideschool.ui.feePayment

import android.app.Activity
import android.app.Application
import android.content.Context
import android.util.Log
import android.widget.Toast
import com.myapplication.theguideschool.R
import com.myapplication.theguideschool.model.AppEnvironment
import com.myapplication.theguideschool.model.Payumoney.AppPreference
import com.myapplication.theguideschool.model.Payumoney.BaseApplication
import com.myapplication.theguideschool.model.Payumoney.GenerateHash
import com.myapplication.theguideschool.ui.feePayment.dataClass.StudentFeeDetails
import com.payumoney.core.PayUmoneyConfig
import com.payumoney.core.PayUmoneyConstants
import com.payumoney.core.PayUmoneySdkInitializer
import com.payumoney.sdkui.ui.utils.PayUmoneyFlowManager
import java.text.SimpleDateFormat
import java.util.*

class ProcessPayment {

    private val productName = "School Fee"
    private lateinit var mPaymentParam: PayUmoneySdkInitializer.PaymentParam
    private lateinit var appEnvironment: AppEnvironment

    fun initPayment(
        studentFeeDetails: List<StudentFeeDetails>,
        feeTotal: String,
        application: Application,
        activity: Activity
    ) {
        val payUmoneyConfig = PayUmoneyConfig.getInstance()
        payUmoneyConfig.disableExitConfirmation(false)
        val payUmoneySdkInitializer = PayUmoneySdkInitializer.PaymentParam.Builder()
        val time = SimpleDateFormat("dd/MM/yyyy").format(Date()).toString()
        appEnvironment = (application as BaseApplication).get_AppEnvironment()
        studentFeeDetails.map {
            val txnID = time + it.studentMobileNumber
            payUmoneySdkInitializer.setAmount(feeTotal)
                .setTxnId(txnID)
                .setProductName(productName)
                .setFirstName(it.studentName)
                .setEmail(it.studentEmail)
                .setPhone(it.studentMobileNumber)
                .setfUrl(appEnvironment.furl())
                .setsUrl(appEnvironment.furl())
                .setIsDebug(appEnvironment.debug())
                .setKey(appEnvironment.merchant_Key())
                .setMerchantId(appEnvironment.merchant_ID())
                .setUdf1("")
                .setUdf2("")
                .setUdf3("")
                .setUdf4("")
                .setUdf5("")
                .setUdf6("")
                .setUdf7("")
                .setUdf8("")
                .setUdf9("")
                .setUdf10("")
        }
        try {
            mPaymentParam = payUmoneySdkInitializer.build()
            mPaymentParam = calculateServerSideHashAndInitiatePayment1(mPaymentParam, application)
            if (AppPreference.selectedTheme == -1) {
                PayUmoneyFlowManager.startPayUMoneyFlow(
                    mPaymentParam, activity, AppPreference.selectedTheme, true
                )
            } else {
                PayUmoneyFlowManager.startPayUMoneyFlow(
                    mPaymentParam, activity, R.style.AppTheme_default, true
                )
            }
        } catch (e: Exception) {
            // some exception occurred
            Log.e("PayUFail", "${e.message}")
            Toast.makeText(activity, e.message, Toast.LENGTH_LONG).show()
        }
    }

    private fun calculateServerSideHashAndInitiatePayment1(
        paymentParam: PayUmoneySdkInitializer.PaymentParam,
        application: Application
    )
            : PayUmoneySdkInitializer.PaymentParam {

        val stringBuilder = StringBuilder()
        val params = paymentParam.params
        stringBuilder.append(params[PayUmoneyConstants.KEY] + "|")
        stringBuilder.append(params[PayUmoneyConstants.TXNID] + "|")
        stringBuilder.append(params[PayUmoneyConstants.AMOUNT] + "|")
        stringBuilder.append(params[PayUmoneyConstants.PRODUCT_INFO] + "|")
        stringBuilder.append(params[PayUmoneyConstants.FIRSTNAME] + "|")
        stringBuilder.append(params[PayUmoneyConstants.EMAIL] + "|")
        stringBuilder.append(params[PayUmoneyConstants.UDF1] + "|")
        stringBuilder.append(params[PayUmoneyConstants.UDF2] + "|")
        stringBuilder.append(params[PayUmoneyConstants.UDF3] + "|")
        stringBuilder.append(params[PayUmoneyConstants.UDF4] + "|")
        stringBuilder.append(params[PayUmoneyConstants.UDF5] + "|")
        stringBuilder.append(params[PayUmoneyConstants.UDF6] + "|")
        stringBuilder.append(params[PayUmoneyConstants.UDF7] + "|")
        stringBuilder.append(params[PayUmoneyConstants.UDF8] + "|||")

        val appEnvironment = (application as BaseApplication).get_AppEnvironment()
        stringBuilder.append(appEnvironment.salt())

        val hash = GenerateHash.hashCal(stringBuilder.toString())
        paymentParam.setMerchantHash(hash)

        return paymentParam
    }
}