package com.myapplication.theguideschool.ui.feePayment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.myapplication.theguideschool.R
import com.myapplication.theguideschool.ui.feePayment.dataClass.StudentFeeDetails
import com.myapplication.theguideschool.ui.feePayment.fragments.AddStudentFragment
import com.myapplication.theguideschool.ui.feePayment.fragments.StudentListFragment
import com.myapplication.theguideschool.ui.feePayment.viewmodel.FeePaymentViewModel
import com.payumoney.core.entity.TransactionResponse
import com.payumoney.sdkui.ui.utils.PayUmoneyFlowManager
import com.payumoney.sdkui.ui.utils.ResultModel
import kotlinx.android.synthetic.main.feepayment.*
import org.json.JSONObject

class FeeActivity3 : AppCompatActivity() {

    private var viewModel: FeePaymentViewModel? = null
    private var studentDetailsList:List<StudentFeeDetails>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.feepayment)
        setupViewModel()
    }

    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(this).get(FeePaymentViewModel::class.java)
        viewModel?.getstudentCount(this)?.observe(this, Observer {
            if (it <= 0) {
                setupToolBar("Add Student")
                supportFragmentManager.beginTransaction()
                    .replace(R.id.feepaymentContainer, AddStudentFragment()).commit()
            } else {
                setupToolBar("Pay Fee ")
                supportFragmentManager.beginTransaction()
                    .replace(R.id.feepaymentContainer, StudentListFragment()).commit()
            }
        })
    }

    private fun setupToolBar(title: String) {
        setSupportActionBar(toolbar)
        supportActionBar?.let {
            it.title = title
            it.elevation = 4.0F
        }
    }

    private fun getStudentsList(){
        viewModel?.studentList?.observe(this, Observer {
            studentDetailsList = it
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.d("Fee_Activity", "request code $requestCode resultcode $resultCode")

        if (requestCode == PayUmoneyFlowManager.REQUEST_CODE_PAYMENT && resultCode == AppCompatActivity.RESULT_OK && data != null) {
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
                val payuResponse = transactionResponse.getPayuResponse()
                val jsonReader = JSONObject(payuResponse)
                val status_reponse = jsonReader.getJSONObject("result")
                val paymentID = status_reponse.getString("paymentId")
                val status = status_reponse.getString("status")
                val txnID = status_reponse.getString("txnid")
                val final_amount = status_reponse.getDouble("amount")
                val paidOn = status_reponse.getString("addedon")
                // Response from SURl and FURL
                val merchantResponse = transactionResponse.transactionDetails
                viewModel?.showPaymentDialog(status, txnID, final_amount, this)
                viewModel?.saveTransactionResponse(paymentID,status,txnID,final_amount,paidOn, studentDetailsList,this)
            } else if (resultModel != null && resultModel.error != null) {
                Log.d("Error response", "Error response : " + resultModel.error.transactionResponse)
            } else {
                Log.d("Error:", "Both objects are null!")
            }
        }
    }
}
