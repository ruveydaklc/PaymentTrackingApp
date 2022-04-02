package com.example.paymenttrackingapp.Model

import java.time.Period
import kotlin.properties.Delegates

class PaymentType {
    var Id by Delegates.notNull<Int>()
    lateinit var Title:String
    var Period:String?=null
    var Day:Int?=null
}