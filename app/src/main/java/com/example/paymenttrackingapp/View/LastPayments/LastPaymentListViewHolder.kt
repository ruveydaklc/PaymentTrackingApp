package com.example.paymenttrackingapp.View.LastPayments

import android.annotation.SuppressLint
import android.content.Context
import android.text.TextWatcher
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.paymenttrackingapp.Model.Payment
import com.example.paymenttrackingapp.R

class LastPaymentListViewHolder (itemView:View,pList:ArrayList<Payment>,itemClick:(position:Int)->Unit):RecyclerView.ViewHolder(itemView) {


    var tvDate:TextView
    var tvPrice:TextView

    init {
        tvPrice=itemView.findViewById(R.id.tvPriceCh)
        tvDate=itemView.findViewById(R.id.tvDateCh)

        itemView.setOnClickListener { itemClick(adapterPosition) }
    }

    @SuppressLint("SetTextI18n")
    fun bindData(context: Context, item:Payment){
        tvPrice.text=item.Price.toString() + " ₺"
        tvDate.text= "${item.Day_date}.${item.Month_date}.${item.Year_date}"
    }

}

