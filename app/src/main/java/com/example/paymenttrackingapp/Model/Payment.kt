package com.example.paymenttrackingapp.Model

import java.util.*
import kotlin.properties.Delegates

class Payment {
    var Id_h by Delegates.notNull<Int>()
    var Price  by Delegates.notNull<Int>()
    var Day_date:Int?=null
    var Month_date:Int?=null
    var Year_date:Int?=null
}