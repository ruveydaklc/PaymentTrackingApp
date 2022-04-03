package com.example.paymenttrackingapp.Model

import java.io.Serializable
import kotlin.properties.Delegates

class PaymentType :Serializable
{
    var Id by Delegates.notNull<Int>()
    lateinit var Title:String
    var Period:String?=null
    var Day:String?=null
}