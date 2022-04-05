package com.example.paymenttrackingapp.Controller.BLL

import android.content.Context
import com.example.paymenttrackingapp.Controller.DAL.PaymentPag.PaymentOperation
import com.example.paymenttrackingapp.Model.Payment
import com.example.paymenttrackingapp.Model.PaymentType

class PaymentBusinessLogic {
    companion object{
        fun addPayment(context: Context,paymant:Payment,pyType:String){
            val poA=PaymentOperation(context)
            poA.addPayment(paymant,pyType)
        }
        fun getAllPayments(context: Context,ptype:String):ArrayList<Payment>{
            return PaymentOperation(context).getPayments(ptype)
        }
        fun deletePayment(context: Context,id:Int){
            val poD=PaymentOperation(context)
            poD.deletePayment(id)
        }
        fun updateToPatmentType(context: Context,paymant: Payment,pyType: String){
            val poU=PaymentOperation(context)
            poU.updateToPatmentType(paymant,pyType)
        }
    }

}