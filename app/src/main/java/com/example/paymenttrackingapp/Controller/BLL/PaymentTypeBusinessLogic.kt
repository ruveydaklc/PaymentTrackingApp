package com.example.paymenttrackingapp.Controller.BLL

import android.content.Context
import com.example.paymenttrackingapp.Controller.DAL.PaymentTypePag.PaymentTypeOperation
import com.example.paymenttrackingapp.Model.PaymentType

class PaymentTypeBusinessLogic {

    companion object{
        fun addPaymentType(context: Context, payType:PaymentType){
            val ptoA= PaymentTypeOperation(context)
            ptoA.addPaymentType(payType)
        }

        fun getAllPaymentTypes(context: Context):ArrayList<PaymentType>{
            return PaymentTypeOperation(context).getPaymentTypes()
        }

        fun updatePaymentType(context: Context,payType: PaymentType){
            val ptoU= PaymentTypeOperation(context)
            ptoU.updatePaymentType(payType)
        }

        fun deletePaymentType(context: Context,id:Int){
            val ptoD= PaymentTypeOperation(context)
            ptoD.deletePaymentType(id)
        }
    }
}