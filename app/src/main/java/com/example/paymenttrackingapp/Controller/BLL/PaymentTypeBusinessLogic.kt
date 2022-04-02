package com.example.paymenttrackingapp.Controller.BLL

import android.content.Context
import com.example.paymenttrackingapp.Controller.PaymentOperation
import com.example.paymenttrackingapp.Controller.PaymentTypeOperation
import com.example.paymenttrackingapp.Model.PaymentType

class PaymentTypeBusinessLogic {

    companion object{
        fun addPaymentType(context: Context, payType:PaymentType){
                val pto= PaymentTypeOperation(context)
                pto.addPaymentType(payType)
        }

        fun getAllPaymentTypes(context: Context):ArrayList<PaymentType>{
            return PaymentTypeOperation(context).getPaymentTypes()
        }
    }
}