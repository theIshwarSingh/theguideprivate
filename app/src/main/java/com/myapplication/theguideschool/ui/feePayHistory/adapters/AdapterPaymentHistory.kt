package com.myapplication.theguideschool.ui.feePayHistory.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.myapplication.theguideschool.R
import com.myapplication.theguideschool.datalayer.PaymentHistory
import kotlinx.android.synthetic.main.payment_history_card.view.*


class AdapterPaymentHistory(var payment_details: List<PaymentHistory>, var context: Context) :
    RecyclerView.Adapter<AdapterPaymentHistory.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AdapterPaymentHistory.ViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val view = layoutInflater.inflate(R.layout.payment_history_card, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return payment_details.size
    }

    override fun onBindViewHolder(holder: AdapterPaymentHistory.ViewHolder, position: Int) {
        val paymentData = payment_details.get(position)
        with(holder) {
            payment_Status.text = paymentData.status
            stu_name.text = paymentData.first_name
            stu_class.text = paymentData.class_name
            stu_section.text = paymentData.section_name
            paid_on.text = paymentData.paidOn_month
            due_date.text = paymentData.due_month

        }
    }

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val payment_Status = view.payment_status
        val stu_name = view.stu_name
        val stu_class = view.stu_class
        val stu_section = view.stu_section
        val paid_on = view.fee_paid_on
        val due_date = view.fee_due_date
        val fee_amount = view.fee_amount
        val roll_no = view.stu_roll_no
        val edu_charges = view.fee_edu_charge
        val con_charges = view.fee_con_charges
        val othe_charges = view.fee_other_charges
    }
}