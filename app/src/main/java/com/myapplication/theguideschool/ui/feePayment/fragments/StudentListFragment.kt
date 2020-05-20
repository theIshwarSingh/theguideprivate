package com.myapplication.theguideschool.ui.feePayment.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.myapplication.theguideschool.R
import com.myapplication.theguideschool.databinding.FragmentStudentListBinding
import com.myapplication.theguideschool.ui.feePayment.baseObservable.FeePaymentBaseObservable
import com.myapplication.theguideschool.ui.feePayment.baseObservable.StudentDetailBaseObservable
import com.myapplication.theguideschool.ui.feePayment.callbacks.FeePaymentStudentCallback
import com.myapplication.theguideschool.ui.feePayment.viewmodel.FeePaymentViewModel
import java.text.SimpleDateFormat
import java.util.*

class StudentListFragment : Fragment() {

    private var viewModel: FeePaymentViewModel? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val binding =
            DataBindingUtil.inflate<FragmentStudentListBinding>(
                inflater,
                R.layout.fragment_student_list,
                container,
                false
            )
        setupViewModel(binding)
        return binding.root
    }

    private fun setupViewModel(binding: FragmentStudentListBinding) {
        viewModel = ViewModelProviders.of(requireActivity()).get(FeePaymentViewModel::class.java)
        binding.efaAddStudent.setOnClickListener {
            viewModel?.showSearchDialog(this.requireContext())
        }
        setupAdapter(binding)
    }

    private fun setupAdapter(binding: FragmentStudentListBinding) {
        binding.data = FeePaymentBaseObservable(listener).apply {
            viewModel?.getStudentsFromDB(requireContext())
                ?.observe(this@StudentListFragment, Observer {
                    it.map {
                        adapter.addItem(
                            StudentDetailBaseObservable(
                                it.studentID.toString(),
                                it.studentName
                            )
                        )
                    }
                })
        }
    }

    private val listener = object : FeePaymentStudentCallback {
        override fun onStudentCallBack(studentDB: StudentDetailBaseObservable) {
            val feeMonth = SimpleDateFormat("MM/yyyy").format(Date()).toString()
            viewModel?.getStudentFeeDetails(studentDB.studentID.toInt(),feeMonth)
            viewModel?.studentList?.observe(this@StudentListFragment, Observer {
                    if (!it.isNullOrEmpty()) {
                        activity?.let {
                            it.supportFragmentManager.beginTransaction()
                                .replace(R.id.feepaymentContainer, PayFeeFragment())
                                .addToBackStack(StudentListFragment::class.java.simpleName)
                                .commit()
                        }
                    } else {
                        Toast.makeText(requireContext(), "NO Record Found", Toast.LENGTH_LONG).show()
                    }

                })
        }
    }

}
