package com.myapplication.theguideschool.ui.feePayment.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.myapplication.theguideschool.R
import com.myapplication.theguideschool.databinding.FragmentPayFeeBinding
import com.myapplication.theguideschool.ui.feePayment.ProcessPayment
import com.myapplication.theguideschool.ui.feePayment.baseObservable.PayFeeBaseObservable
import com.myapplication.theguideschool.ui.feePayment.dataClass.StudentFeeDetails
import com.myapplication.theguideschool.ui.feePayment.viewmodel.FeePaymentViewModel
import com.payumoney.core.entity.TransactionResponse
import com.payumoney.sdkui.ui.utils.PayUmoneyFlowManager
import com.payumoney.sdkui.ui.utils.ResultModel
import org.json.JSONObject

class PayFeeFragment : Fragment() {

    private var viewModel: FeePaymentViewModel? = null
    private var feeTotal: Double = 0.0
    private var feeID:String = ""
    private var studentDetailsList:List<StudentFeeDetails>? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentPayFeeBinding>(
            inflater,
            R.layout.fragment_pay_fee,
            container,
            false
        )
        viewModel = ViewModelProviders.of(requireActivity()).get(FeePaymentViewModel::class.java)
        setupView(binding)
        return binding.root
    }

    private fun setupView(binding: FragmentPayFeeBinding) {
        viewModel?.studentList?.observe(this@PayFeeFragment, Observer {
            it.map {
                feeTotal = ((it.educationCharges + it.computerCharges
                            + it.musicCharges + it.smartClassCharges
                            + it.convCharges + it.otherCharges
                            + it.facilityCharges + it.annualCharges)
                - it.discount)
                feeID = it.feeID
                binding.data = PayFeeBaseObservable(
                    "₹ ${feeTotal}",
                    "Fee Month: ${it.feeMonth}",
                    it.studentName,
                    it.className,
                    it.sectionName,
                    "₹ ${it.annualCharges}",
                    "₹ ${it.educationCharges}",
                    "₹ ${it.computerCharges}",
                    "₹ ${it.musicCharges}",
                    "₹ ${it.smartClassCharges}",
                    "₹ ${it.convCharges}",
                    "₹ ${it.facilityCharges}",
                    "₹ ${it.otherCharges}",
                    "₹ ${it.discount}"
                )
            }
        })
        binding.btnPayNow.setOnClickListener {
            viewModel?.studentList?.observe(this@PayFeeFragment, Observer {
                studentDetailsList = it
                activity?.let { activity ->
                    ProcessPayment().initPayment(
                        it,
                        feeTotal.toString(),
                        activity.application,
                        activity
                    )
                }
            })
        }
    }
}