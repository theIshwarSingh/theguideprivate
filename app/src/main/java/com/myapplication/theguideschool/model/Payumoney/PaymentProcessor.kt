package com.myapplication.theguideschool.model.Payumoney

import android.util.Log
import android.widget.Toast
import com.myapplication.theguideschool.model.AppEnvironment
import com.myapplication.theguideschool.R
import com.myapplication.theguideschool.ui.feePayment.FeePaymentActivity
import com.payumoney.core.PayUmoneyConfig
import com.payumoney.core.PayUmoneyConstants
import com.payumoney.core.PayUmoneySdkInitializer
import com.payumoney.sdkui.ui.utils.PayUmoneyFlowManager
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_fee.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class PaymentProcessor : FeePaymentActivity() {

    private var ammount: Double = 0.0
    private var isDisableExitConfirmation: Boolean = false
    private lateinit var mPaymentParam: PayUmoneySdkInitializer.PaymentParam
    private lateinit var appEnvironment: AppEnvironment
    private var compositeDisposable = CompositeDisposable()


    fun launchpayu(
        firstName: String, user_email: String, user_phone: String, user_no: String,
        fee_month: String, user_amount: Double, class_name: String, section_name: String,
        edu_charges: String, conv_charges: String, other_charges: String
    ) {

        val payUmoneyConfig = PayUmoneyConfig.getInstance()
        payUmoneyConfig.disableExitConfirmation(isDisableExitConfirmation)
        val payUmoneySdkInitializer = PayUmoneySdkInitializer.PaymentParam.Builder()

        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("dd - MM - YYYY  HH:mm")
        val formated = current.format(formatter)
        try {
            ammount = user_amount

        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
        val txnId = user_phone + System.currentTimeMillis().toString()
        val phone = user_phone
        val firstname = firstName
        val email = user_email
        val udf1 = user_no
        val udf2 = fee_month
        val udf3 = class_name
        val udf4 = section_name
        val udf5 = edu_charges
        val udf6 = conv_charges
        val udf7 = other_charges
        val udf8 = formated
        val udf9 = ""
        val udf10 = ""
        appEnvironment = (application as BaseApplication).get_AppEnvironment()
        payUmoneySdkInitializer.setAmount(ammount.toString())
            .setTxnId(txnId)
            .setPhone(phone)
            .setProductName(productName)
            .setFirstName(firstname)
            .setEmail(email)
            .setsUrl(appEnvironment.surl())
            .setfUrl(appEnvironment.furl())
            .setUdf1(udf1)
            .setUdf2(udf2)
            .setUdf3(udf3)
            .setUdf4(udf4)
            .setUdf5(udf5)
            .setUdf6(udf6)
            .setUdf7(udf7)
            .setUdf8(udf8)
            .setUdf9(udf9)
            .setUdf10(udf10)
            .setIsDebug(appEnvironment.debug())
            .setKey(appEnvironment.merchant_Key())
            .setMerchantId(appEnvironment.merchant_ID())

        try {
            mPaymentParam = payUmoneySdkInitializer.build()
            mPaymentParam = calculateServerSideHashAndInitiatePayment1(mPaymentParam)
            if (AppPreference.selectedTheme == -1) {
                PayUmoneyFlowManager.startPayUMoneyFlow(
                    mPaymentParam, this, AppPreference.selectedTheme, true
                )
            } else {
                PayUmoneyFlowManager.startPayUMoneyFlow(
                    mPaymentParam, this, R.style.AppTheme_default, true
                )
            }
        } catch (e: Exception) {
            // some exception occurred
            Log.d("#####", "${e.message}")
            Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
            pay_fee.isEnabled = true
        }
    }

    private fun calculateServerSideHashAndInitiatePayment1(paymentParam: PayUmoneySdkInitializer.PaymentParam): PayUmoneySdkInitializer.PaymentParam {

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