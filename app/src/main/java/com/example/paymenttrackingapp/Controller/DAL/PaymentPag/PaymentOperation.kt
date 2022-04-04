package com.example.paymenttrackingapp.Controller.DAL.PaymentPag

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.example.paymenttrackingapp.Controller.DAL.PaymentTypePag.pTypeDatabaseOpenHelper
import com.example.paymenttrackingapp.Model.Payment
import com.example.paymenttrackingapp.Model.PaymentType

class PaymentOperation(context: Context) {
    var PaymentDatabase:SQLiteDatabase?=null
    val dbOpenHelper:pTypeDatabaseOpenHelper

    val dbName="PaymentDb"
    val tableName="Payment"

    init {
        dbOpenHelper= pTypeDatabaseOpenHelper(context,dbName,null,1)
    }

    fun open(){
        PaymentDatabase =dbOpenHelper.writableDatabase
    }

    fun close(){
        if (PaymentDatabase != null && PaymentDatabase!!.isOpen){
            PaymentDatabase!!.close()
        }
    }

    fun getAllPayments(ptype: String):Cursor{
        val queryP = "SELECT * FROM Payment WHERE ptTitle = '${ptype}' "
        return PaymentDatabase!!.rawQuery(queryP,null)
    }

    @SuppressLint("Range")
    fun getPayments(ptype:String):ArrayList<Payment>{
        val paymentList=ArrayList<Payment>()
        var p: Payment
        open()
        var cursor:Cursor = getAllPayments(ptype)
        if (cursor.moveToFirst()){
            do {
                p= Payment(cursor.getInt(0))
                p.Price=cursor.getInt(cursor.getColumnIndex("Price"))
                p.Day_date=cursor.getInt(cursor.getColumnIndex("Day_date"))
                p.Month_date=cursor.getInt(cursor.getColumnIndex("Month_date"))
                p.Year_date=cursor.getInt(cursor.getColumnIndex("Year_date"))
                p.ptTitle=cursor.getString(cursor.getColumnIndex("ptTitle"))

                paymentList.add(p)
            }while (cursor.moveToNext())
        }
        close()
        return paymentList
    }

    fun addPayment(p:Payment,pymType: String){
        val cv =ContentValues()
        cv.put("Year_date",p.Year_date)
        cv.put("Month_date",p.Month_date)
        cv.put("Day_date",p.Day_date)
        cv.put("Price",p.Price)
        cv.put("ptTitle",pymType)
        open()
        PaymentDatabase!!.insert(tableName,null,cv)
        close()
    }

    fun deletePayment(id:Int){
        open()
        PaymentDatabase!!.delete(tableName,"Id = ?", arrayOf(id.toString()))
        close()
    }

    fun updateToPatmentType(payment:Payment,pymType: String){
        val cv = ContentValues()
        /*cv.put("Year_date",pymt.Year_date)
        cv.put("Month_date",pymt.Month_date)
        cv.put("Day_date",pymt.Day_date)
        cv.put("Price",pymt.Price)
        cv.put("ptTitle",pymt.ptTitle)*/

        cv.put("Year_date",payment.Year_date)
        cv.put("Month_date",payment.Month_date)
        cv.put("Day_date",payment.Day_date)
        cv.put("Price",payment.Price)
        cv.put("ptTitle",pymType)
        open()
        PaymentDatabase!!.update(tableName,cv,"Id = ?", arrayOf(payment.Id.toString()))
        close()
    }



}