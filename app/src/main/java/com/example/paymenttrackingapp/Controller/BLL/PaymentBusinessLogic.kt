package com.example.paymenttrackingapp.Controller.BLL

import android.content.Context
import com.example.paymenttrackingapp.Controller.DAL.PaymentPag.PaymentOperation
import com.example.paymenttrackingapp.Model.Payment

class PaymentBusinessLogic {
    companion object{
        fun addPayment(context: Context,paymant:Payment){
            val poA=PaymentOperation(context)
            poA.addPayment(paymant)
        }
        fun getAllPayments(context: Context,ptype:String):ArrayList<Payment>{
            return PaymentOperation(context).getPayments(ptype)
        }
        fun deletePayment(context: Context,id:Int){
            val poD=PaymentOperation(context)
            poD.deletePayment(id)
        }



    }

}