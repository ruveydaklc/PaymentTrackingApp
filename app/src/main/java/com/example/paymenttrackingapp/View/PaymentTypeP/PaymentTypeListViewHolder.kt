package com.example.paymenttrackingapp.View.PaymentTypeP

import android.content.Context
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.paymenttrackingapp.Model.PaymentType
import com.example.paymenttrackingapp.R

class PaymentTypeListViewHolder(itemView: View, ptList:ArrayList<PaymentType>, itemClick:(position:Int)->Unit,addItemClick:(position:Int)->Unit):RecyclerView.ViewHolder(itemView) {

    var btnPayAdd:Button
    var tvType:TextView
    var tvPeriod:TextView
    var tvDay:TextView

    init {

        btnPayAdd=itemView.findViewById(R.id.btnAddpaymentCt)
        tvType=itemView.findViewById(R.id.tvTypeCt)
        tvPeriod=itemView.findViewById(R.id.tvPeriodCt)
        tvDay=itemView.findViewById(R.id.tvPeriodDayCt)

        //cardView.setOnClickListener { itemClick(adapterPosition,ptList) }

        itemView.setOnClickListener { itemClick(adapterPosition) }

        btnPayAdd.setOnClickListener { addItemClick(adapterPosition) }
    }


    fun bindData(context:Context,item:PaymentType){

        /*btnPayAdd.setOnClickListener {

           val intent=Intent(context,AddPayActivity::class.java)
            intent.putExtra("item", item)
            context.startActivity(intent)
        }*/
        tvType.text=item.Title
        tvPeriod.text=item.Period
        tvDay.text="${item.Day}. gün"



    }
}