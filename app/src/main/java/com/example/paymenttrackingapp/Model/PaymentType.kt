package com.example.paymenttrackingapp.Model

import java.io.Serializable

class PaymentType(var Id: Int?)  :Serializable
{
    lateinit var Title:String
    var Period:String?=null
    var Day:String?=null
}