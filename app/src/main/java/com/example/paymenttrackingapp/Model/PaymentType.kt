package com.example.paymenttrackingapp.Model

import java.io.Serializable
import java.time.Period
import kotlin.properties.Delegates

class PaymentType :Serializable{
    var Id by Delegates.notNull<Int>()
    var Title:String?=null
    var Period:String?=null
    var Day:Int= 1
}