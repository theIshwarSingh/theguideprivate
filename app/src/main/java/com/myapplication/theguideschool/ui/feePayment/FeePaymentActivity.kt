package com.myapplication.theguideschool.ui.feePayment


import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.myapplication.theguideschool.model.AppEnvironment
import com.myapplication.theguideschool.model.Payumoney.AppPreference
import com.myapplication.theguideschool.model.Payumoney.BaseApplication
import com.myapplication.theguideschool.model.Payumoney.GenerateHash
import com.myapplication.theguideschool.model.Payumoney.PaymentProcessor
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
import kotlinx.android.synthetic.main.activity_fee.*
import kotlinx.android.synthetic.main.payment_reponse.view.*
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

open class FeePaymentActivity : AppCompatActivity() {

    private var ammount: Double = 0.0
    var productName = "School Fee"
    private var isDisableExitConfirmation: Boolean = false
    private lateinit var mPaymentParam: PayUmoneySdkInitializer.PaymentParam
    private lateinit var appEnvironment: AppEnvironment
    private var compositeDisposable = CompositeDisposable()
    var calender = Calendar.getInstance()
    private lateinit var editText_conv_charges: String
    private lateinit var getEditText_other_charges: String
    private var processPayment = PaymentProcessor()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fee)
        spinnerValues()
        getfee_amount()
        initProcess()
    }

    fun spinnerValues() {
        //Class Selection Spinner
        var editText_class: String
        val class_name = findViewById<Spinner>(R.id.spinner_class)
        val myAdapter = ArrayAdapter(this@FeePaymentActivity, android.R.layout.simple_list_item_1, resources.getStringArray(R.array.class_name_array))
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        class_name.adapter = myAdapter
        class_name.onItemSelectedListener = object : AdapterView.OnItemSelectedListener { override fun onNothingSelected(parent: AdapterView<*>?) {}
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
//                editText_class = getString(position)
            }
        }
        //Section Selection Spinner
        val section_name = findViewById<Spinner>(R.id.spinner_section)
        val myAdapter2 = ArrayAdapter(
            this@FeePaymentActivity,
            android.R.layout.simple_list_item_1,
            resources.getStringArray(R.array.section_name_array)
        )
        myAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        section_name.adapter = myAdapter2

    }

    @SuppressLint("SetTextI18n")
    private fun initProcess() {
        val dateSetListener = object : DatePickerDialog.OnDateSetListener {
            override fun onDateSet(view: DatePicker?,
                year: Int,
                monthOfYear: Int,
                dayOfMonth: Int
            ) {
                calender.set(Calendar.YEAR, year)
                calender.set(Calendar.MONTH, monthOfYear)
                calender.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                updateDateInView()
            }
        }
        fee_month_year.setOnClickListener {
            DatePickerDialog(
                this, dateSetListener, calender.get(Calendar.YEAR),
                calender.get(Calendar.MONTH), calender.get(Calendar.DAY_OF_MONTH)
            ).show()
        }

        pay_fee.setOnClickListener {
            val editText_Stu_name = stu_name.text.toString()
            val editText_email = email_adr.text.toString()
            val editText_number = mobile_no.text.toString()
            val editText_rollno = stu_roll_no.text.toString()
            val editText_class = spinner_class.selectedItem.toString()
            val editText_section = spinner_section.selectedItem.toString()
            val editText_edu_charges = edu_charges.text.toString()
            editText_conv_charges = conv_charges.text.toString()
            getEditText_other_charges = other_charges.text.toString()
            val final_amount = getfee_amount().toDouble()
            val month_year_fee = fee_month_year.text.toString()
            // Create an OnDateSetListener
            processPayment.launchpayu(firstName = editText_Stu_name,
                user_email = editText_email,
                user_phone = editText_number,
                user_no = editText_rollno,
                fee_month = month_year_fee,
                user_amount = final_amount,
                class_name = editText_class,
                section_name = editText_section,
                edu_charges = editText_edu_charges,
                conv_charges = editText_conv_charges,
                other_charges = getEditText_other_charges)
        }
    }

    private fun updateDateInView() {
        val myFormat = "dd - MMM - yyyy" // mention the format you need
        val sdf = SimpleDateFormat(myFormat, Locale.getDefault())
        fee_month_year.text = sdf.format(calender.time)
    }


    private fun getfee_amount(): String {
        other_charges.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                try {
                    val edu_ch = edu_charges.text.toString().toDouble()
                    val con_ch = conv_charges.text.toString().toDouble()
                    val other_ch = other_charges.text.toString().toDouble()
                    val price = edu_ch + con_ch + other_ch
                    amount.text = price.toString()
                    ammount = price
                } catch (e: Exception) {
                    Toast.makeText(this@FeePaymentActivity, "Enter all fields", Toast.LENGTH_LONG).show()
                }

            }

            override fun afterTextChanged(s: Editable) {

            }
        })

        conv_charges.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                // textView_total_fee.setText(price.toString());
            }

            override fun afterTextChanged(s: Editable) {
                try {
                    val edu_ch = edu_charges.text.toString().toDouble()
                    val con_ch = conv_charges.text.toString().toDouble()
                    val other_ch = other_charges.text.toString().toDouble()
                    val price = edu_ch + con_ch + other_ch
                    amount.text = price.toString()
                    ammount = price
                } catch (e: Exception) {
                    Toast.makeText(this@FeePaymentActivity, "Enter Education Charges ", Toast.LENGTH_LONG)
                        .show()
                }

            }
        })

        return ammount.toString()
    }

    private fun launchpayu(
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.d("Fee_Activity", "request code $requestCode resultcode $resultCode")

        if (requestCode == PayUmoneyFlowManager.REQUEST_CODE_PAYMENT && resultCode == RESULT_OK && data != null) {
            val transactionResponse = data.getParcelableExtra<TransactionResponse>(PayUmoneyFlowManager.INTENT_EXTRA_TRANSACTION_RESPONSE)
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
//
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
//                                conv_charges = editText_conv_charges,
//                                other_charge = getEditText_other_charges,
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