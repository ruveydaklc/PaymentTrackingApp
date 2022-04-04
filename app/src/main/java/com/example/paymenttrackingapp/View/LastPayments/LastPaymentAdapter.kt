package com.example.paymenttrackingapp.View.LastPayments

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.paymenttrackingapp.Model.Payment
import com.example.paymenttrackingapp.Model.PaymentType
import com.example.paymenttrackingapp.R
import com.example.paymenttrackingapp.View.PaymentTypeP.PaymentTypeListViewHolder

class LastPaymentAdapter(val context: Context, var paymentList:ArrayList<Payment>, val itemClick:(position:Int)->Unit):
    RecyclerView.Adapter<LastPaymentListViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LastPaymentListViewHolder {
        val view =LayoutInflater.from(context).inflate(R.layout.payment_history_line_design,parent,false)
        return LastPaymentListViewHolder(view,paymentList,itemClick)
    }

    override fun onBindViewHolder(holder: LastPaymentListViewHolder, position: Int) {
        holder.bindData(context,paymentList.get(position))
    }

    override fun getItemCount(): Int {
        return paymentList.size
    }


}