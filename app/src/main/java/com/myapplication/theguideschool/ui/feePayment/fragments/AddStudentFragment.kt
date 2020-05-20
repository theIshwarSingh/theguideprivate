package com.myapplication.theguideschool.ui.feePayment.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.myapplication.theguideschool.R
import com.myapplication.theguideschool.ui.feePayment.viewmodel.FeePaymentViewModel
import kotlinx.android.synthetic.main.fragment_add_student.view.*

class AddStudentFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_add_student, container, false)
        val viewModel =
            ViewModelProviders.of(requireActivity()).get(FeePaymentViewModel::class.java)
        searchStudentCall(view, viewModel)
        return view
    }

    private fun searchStudentCall(view: View, viewModel: FeePaymentViewModel) {
        view.btn_addStudent.setOnClickListener {
            viewModel.showSearchDialog(this.requireContext())
        }
    }
}
