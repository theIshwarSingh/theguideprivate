package com.myapplication.theguideschool.ui.feePayment.viewmodel

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.myapplication.theguideschool.R
import com.myapplication.theguideschool.api.TheGuideCall
import com.myapplication.theguideschool.datalayer.AppDB
import com.myapplication.theguideschool.datalayer.PaymentHistory
import com.myapplication.theguideschool.datalayer.StudentDetailsDB
import com.myapplication.theguideschool.ui.feePayment.dataClass.StudentFeeDetails
import com.myapplication.theguideschool.ui.feePayment.dataClass.StudentsDetails
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.item_dialog_add_student.view.*
import kotlinx.android.synthetic.main.payment_reponse.view.*

class FeePaymentViewModel : ViewModel() {

    private var disposable: Disposable? = null
    private var theGuideCall = TheGuideCall()
    private var compositeDisposable = CompositeDisposable()
    private val liveStudentFeeDetails = MutableLiveData<List<StudentFeeDetails>>()
    val studentList: LiveData<List<StudentFeeDetails>> = liveStudentFeeDetails

    fun getstudentCount(context: Context): LiveData<Int>? {
        val db = AppDB.getAppDataBase(context)?.studentDetailsDB()
        val getCount = db?.getStudentCount()
        return getCount
    }

    fun showSearchDialog(context: Context) {
        val dialogview =
            LayoutInflater.from(context).inflate(R.layout.item_dialog_add_student, null)
        val dialog = MaterialAlertDialogBuilder(context).setView(dialogview).create()
        dialog.show()
        dialogview.btn_searchStudent.setOnClickListener {
            val studentID = dialogview.et_StudentID.text.toString()
            searchStudent(studentID.toInt(), context)
            dialog.dismiss()
        }
    }

    fun showPaymentDialog(status: String, txnID: String, final_amount: Double, context: Context) {
        val mDialogView = LayoutInflater.from(context).inflate(R.layout.payment_reponse, null)
        val dialog = MaterialAlertDialogBuilder(context).setView(mDialogView).create()
        dialog.show()
        mDialogView.pay_status.text = status
        mDialogView.txn_id.text = txnID
        mDialogView.pay_amount.text = final_amount.toString()
        mDialogView.close_details.setOnClickListener {
            dialog.dismiss()
        }
    }

    private fun searchStudent(stuID: Int, context: Context) {
        disposable = theGuideCall.getStudentDetails(stuID)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                if (it.isSuccessful) {
                    addStudentToDB(it.body(), context)
                }
            }

    }

    private fun addStudentToDB(studentList: List<StudentsDetails>?, context: Context) {
        compositeDisposable.add(
            Observable.create<Unit> {
                try {
                    val db = AppDB.getAppDataBase(context)
                    val addstudent = db?.studentDetailsDB()
                    studentList?.map {
                        with(addstudent) {
                            this?.insertStudentsDetails(
                                StudentDetailsDB(
                                    studentID = it.studentID.toInt(),
                                    studentName = it.studentName,
                                    studentEmail = it.studentEmail,
                                    studentPhone = it.studentMobileNumber
                                )
                            )
                        }
                    }
                } catch (e: Exception) {
                    it.onError(e)
                }
                it.onComplete()
            }.subscribeOn(Schedulers.io())
                .subscribe()
        )
    }

    fun getStudentsFromDB(context: Context): LiveData<List<StudentDetailsDB>>? {
        val studentDB = AppDB.getAppDataBase(context)?.studentDetailsDB()
        val studentsDetails = studentDB?.showStudentDetails()
        return studentsDetails
    }

    fun getStudentFeeDetails(studentID: Int, feeMonth: String) {
        disposable = theGuideCall.getStudentDetails(studentID, feeMonth)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                if (it.isSuccessful) {
                    if (it.body().isNullOrEmpty()){

                    }
                    liveStudentFeeDetails.value = it.body()
                }
            }
    }

    fun saveTransactionResponse(
        paymentID: String,
        status: String,
        txnID: String,
        amount: Double,
        paidOn: String,
        studentFeeDetails: List<StudentFeeDetails>?,
        context: Context
    ) {
        compositeDisposable.add(
            Observable.create<Unit> {
                try {
                    val db = AppDB.getAppDataBase(context)
                    val paymentHistory = db?.paymenthistory()
                    studentFeeDetails?.map {studentDetails->
                    with(paymentHistory) {
                            this?.insert_payment_details(
                                PaymentHistory(
                                    0,
                                    payment_id = paymentID,
                                    status = status,
                                    txnid = txnID,
                                    first_name = studentDetails.studentName,
                                    class_name = studentDetails.className,
                                    section_name = studentDetails.sectionName,
                                    due_month = studentDetails.feeMonth,
                                    annualCharge = studentDetails.annualCharges,
                                    educationCharges = studentDetails.educationCharges,
                                    computerCharges = studentDetails.computerCharges,
                                    musicCharges= studentDetails.musicCharges,
                                    smartClassCharges = studentDetails.smartClassCharges,
                                    convCharges = studentDetails.convCharges,
                                    facilityCharges = studentDetails.facilityCharges,
                                    otherCharges = studentDetails.otherCharges,
                                    discount = studentDetails.discount,
                                    amount = amount,
                                    paidOn_month = paidOn
                                )
                            )
                        }
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