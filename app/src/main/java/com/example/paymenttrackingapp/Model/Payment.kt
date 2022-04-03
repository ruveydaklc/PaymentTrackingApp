package com.example.paymenttrackingapp.Model

import java.io.Serializable

class Payment(var Id_h:Int,var Price:Int) : Serializable
{
    var Day_date:Int?=null
    var Month_date:Int?=null
    var Year_date:Int?=null
}