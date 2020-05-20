package com.myapplication.theguideschool.ui.feePayment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.myapplication.theguideschool.model.AppEnvironment
import com.myapplication.theguideschool.model.Payumoney.AppPreference
import com.myapplication.theguideschool.model.Payumoney.BaseApplication
import com.myapplication.theguideschool.model.Payumoney.GenerateHash
import com.myapplication.theguideschool.R
import com.myapplication.theguideschool.datalayer.AppDB
import com.myapplication.theguideschool.datalayer.PaymentHistory
import com.payumoney.core.PayUmoneyConfig
import com.payumoney.core.PayUmoneyConstants
import com.payumoney.core.PayUmoneySdkInitializer
import com.payumoney.core.entity.TransactionResponse
import com.payumoney.sdkui.ui.utils.PayUmoneyFlowManager
import com.payumoney.sdkui.ui.utils.ResultModel
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_fee_payment_actiivity2.*
import kotlinx.android.synthetic.main.activity_fee_payment_actiivity2.amount
import kotlinx.android.synthetic.main.payment_reponse.view.*
import org.json.JSONObject
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class FeePaymentActiivity2 : AppCompatActivity() {

    private var feeAmount: Double = 0.0
    private val productName = "School Fee"
    private var isDisableExitConfirmation: Boolean = false
    private lateinit var mPaymentParam: PayUmoneySdkInitializer.PaymentParam
    private lateinit var appEnvironment: AppEnvironment
    private var compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fee_payment_actiivity2)
        setupToolBar()
        getClassName()
        initPayment()
    }

    fun setupToolBar() {
        setSupportActionBar(toolbar)
        supportActionBar?.let {
            it.title = "FEE PAYMENT"
            it.elevation = 4.0F
        }
    }


    fun getClassName() {
        val class_name = findViewById<Spinner>(R.id.spinnerClass)
        val myAdapter = ArrayAdapter(this@FeePaymentActiivity2,android.R.layout.simple_list_item_1, resources.getStringArray(R.array.class_name_array))
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        class_name.adapter = myAdapter
        class_name.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                when (position) {
                    0, 1, 2 -> {
                        amount.text = "₹ 950"
                        feeAmount = 950.00
                    }
                    3, 4, 5, 6, 7 -> {
                        amount.text = "₹ 1150"
                        feeAmount = 1150.00
                    }
                    8, 9, 10 -> {
                        amount.text = "₹ 1200"
                        feeAmount = 1200.00
                    }
                    11, 12 -> {
                        amount.text = "₹ 1250"
                        feeAmount = 1250.00
                    }
                    13, 14 -> {
                        amount.text = "₹ 2150"
                        feeAmount = 2150.00
                    }
                }

            }

        }
    }

    fun initPayment() {
        btn_pay_fee.setOnClickListener {
            val stuName = et_stu_name.text.toString()
            var stuEmail: String = et_email_adr.text.toString()
            val stuMobileNumber = et_mobile_no.text.toString()
            val editText_class = spinnerClass.selectedItem.toString()
            if (stuEmail.isEmpty() && stuName.isEmpty() && stuMobileNumber.isEmpty()) {
                stuEmail = "theguideschool@gmail.com"
                return@setOnClickListener
            }
            processFeepayment(stuName, stuEmail, stuMobileNumber, editText_class)
        }
    }

    private fun processFeepayment(
        stuName: String,
        stuEmail: String,
        stuMobile: String,
        stuClass: String
    ) {
        val payUmoneyConfig = PayUmoneyConfig.getInstance()
        payUmoneyConfig.disableExitConfirmation(isDisableExitConfirmation)
        val payUmoneySdkInitializer = PayUmoneySdkInitializer.PaymentParam.Builder()

        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("dd - MM - YYYY ")
        val formated = current.format(formatter)
        try {
            feeAmount

        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
        val txnId = stuMobile + System.currentTimeMillis().toString()
        val phone = stuMobile
        val firstname = stuName
        val email = stuEmail
        val udf1 = formated
        val udf2 = stuClass
        val udf3 = ""
        val udf4 = ""
        val udf5 = ""
        val udf6 = ""
        val udf7 = ""
        val udf8 = ""
        val udf9 = ""
        val udf10 = ""
        appEnvironment = (application as BaseApplication).get_AppEnvironment()
        payUmoneySdkInitializer.setAmount(feeAmount.toString())
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
            btn_pay_fee.isEnabled = true
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.d("Fee_Activity", "request code $requestCode resultcode $resultCode")

        if (requestCode == PayUmoneyFlowManager.REQUEST_CODE_PAYMENT && resultCode == RESULT_OK && data != null) {
            val transactionResponse =
                data.getParcelableExtra<TransactionResponse>(PayUmoneyFlowManager.INTENT_EXTRA_TRANSACTION_RESPONSE)
            val resultModel = data.getParcelableExtra<ResultModel>(PayUmoneyFlowManager.ARG_RESULT)
            // Check which object is non-null
            if (transactionResponse != null && transactionResponse.getPayuResponse() != null) {
                if (transactionResponse.transactionStatus == TransactionResponse.TransactionStatus.SUCCESSFUL) {
                    //Success Transaction
                } else {
                    //Failure Transaction
                }
                // Response from Payumoney
//                val (status, txnID, final_amount) = handleResponsetoDB(transactionResponse)
                // Response from SURl and FURL
                val merchantResponse = transactionResponse.transactionDetails
//                showDialog(status, txnID, final_amount)
            } else if (resultModel != null && resultModel.error != null) {
                Log.d("Error response", "Error response : " + resultModel.error.transactionResponse)
            } else {
                Log.d("Error:", "Both objects are null!")
            }
        }
    }

    private fun showDialog(status: String, txnID: String, final_amount: Double) {
        val mDialogView = LayoutInflater.from(this).inflate(R.layout.payment_reponse, null)
        val mBuilder = AlertDialog.Builder(this).setView(mDialogView)
        val alertDialog = mBuilder.show()
        mDialogView.pay_status.text = status
        mDialogView.txn_id.text = txnID
        mDialogView.pay_amount.text = final_amount.toString()
        mDialogView.close_details.setOnClickListener {
            alertDialog.dismiss()
        }
    }

//    private fun handleResponsetoDB(transactionResponse: TransactionResponse): Triple<String, String, Double> {
//        val payuResponse = transactionResponse.getPayuResponse()
//        val jsonReader = JSONObject(payuResponse)
//        val status_reponse = jsonReader.getJSONObject("result")
//        val paymentID = status_reponse.getString("paymentId")
//        val status = status_reponse.getString("status")
//        val txnID = status_reponse.getString("txnid")
//        val firstName = status_reponse.getString("firstname")
//        val email = status_reponse.getString("email")
//        val phone = status_reponse.getString("phone")
//        val class_name = status_reponse.getString("udf3")
//        val section_name = status_reponse.getString("udf4")
//        val stu_roll_no = status_reponse.getString("udf1")
//        val due_date = status_reponse.getString("udf2")
//        val education_fee = status_reponse.getString("udf5")
//        val final_amount = status_reponse.getDouble("amount")
//        val paidOn = status_reponse.getString("addedon")
//
//        compositeDisposable.add(
//            Observable.create<Unit> {
//                try {
//                    val db = AppDB.getAppDataBase(this)
//                    val paymentHistory = db?.paymenthistory()
//                    with(paymentHistory) {
//                        this?.insert_payment_details(
//                            PaymentHistory(
//                                0,
//                                payment_id = paymentID,
//                                status = status,
//                                txnid = txnID,
//                                first_name = firstName,
//                                class_name = class_name,
//                                section_name = section_name,
//                                email = email,
//                                roll_no = stu_roll_no,
//                                due_month = due_date,
//                                edu_charges = education_fee,
//                                conv_charges = "0",
//                                other_charge = "0",
//                                amount = final_amount,
//                                paidOn_month = paidOn,
//                                phone = phone
//                            )
//                        )
//                    }
//                    it.onNext(Unit)
//                } catch (e: Exception) {
//                    it.onError(e)
//                    Log.e("DB", e.message.toString())
//                }
//                it.onComplete()
//            }.subscribeOn(Schedulers.io())
//                .subscribe()
//        )
//        return Triple(status, txnID, final_amount)
//    }
}
