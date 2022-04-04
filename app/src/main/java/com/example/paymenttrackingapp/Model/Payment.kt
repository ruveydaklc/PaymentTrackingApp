package com.example.paymenttrackingapp.Model

import java.io.Serializable

class Payment(var Id:Int?) : Serializable
{
    var Price:Int=0
    var Day_date:Int?=null
    var Month_date:Int?=null
    var Year_date:Int?=null
    var ptTitle:String?=null
}