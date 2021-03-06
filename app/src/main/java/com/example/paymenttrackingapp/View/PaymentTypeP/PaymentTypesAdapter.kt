package com.example.paymenttrackingapp.View.PaymentTypeP

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.paymenttrackingapp.Model.PaymentType
import com.example.paymenttrackingapp.R

class PaymentTypesAdapter(val context: Context,var paymentTypesList:ArrayList<PaymentType>,val itemClick:(position:Int)->Unit, val addItemClick:(position:Int)->Unit):RecyclerView.Adapter<PaymentTypeListViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaymentTypeListViewHolder {

        val view=LayoutInflater.from(context).inflate(R.layout.payment_type_line_design,parent,false)
        return PaymentTypeListViewHolder(view,paymentTypesList,itemClick,addItemClick)
    }

    override fun onBindViewHolder(holder: PaymentTypeListViewHolder, position: Int) {
        holder.bindData(context,paymentTypesList.get(position))
    }

    override fun getItemCount(): Int {
        return paymentTypesList.size
    }
}