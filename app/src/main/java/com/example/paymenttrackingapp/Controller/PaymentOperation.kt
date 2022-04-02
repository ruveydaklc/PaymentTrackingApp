package com.example.paymenttrackingapp.Controller

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.example.paymenttrackingapp.Model.Payment

class PaymentOperation(context: Context) {
    var PaymentDatabase:SQLiteDatabase?=null
    val dbOpenHelper: pTypeDatabaseOpenHelper

    val dbName="PaymentDb"
    val tableName="Payment"
    init {

        dbOpenHelper= pTypeDatabaseOpenHelper(context,dbName,null,1)
    }

    fun open(){
        PaymentDatabase=dbOpenHelper.writableDatabase
    }

    fun close(){
        if (PaymentDatabase != null && PaymentDatabase!!.isOpen){
            PaymentDatabase!!.close()
        }
    }

    fun savePayment(p:Payment){

        val cv=ContentValues()
        cv.put("Price",p.Price)
        cv.put("Day_date",p.Day_date)
        cv.put("Month_date",p.Month_date)
        cv.put("Year_date",p.Year_date)
        open()
        val record = PaymentDatabase!!.insert(tableName,null,cv)
        close()

    }

    fun deletePayment(id:Int){
        open()
        PaymentDatabase!!.delete(tableName,"Id=?", arrayOf(id.toString()))
        close()
    }

    fun getAllPayments(): Cursor {
        val query = "SELECT * FROM Payment"
        return PaymentDatabase!!.rawQuery(query, null)
    }

    @SuppressLint("Range")
    fun getPayments():ArrayList<Payment> {
        val paymentList = ArrayList<Payment>()
        var p: Payment
        open()
        var cursor: Cursor = getAllPayments()
        if (cursor.moveToFirst()) {
            do {
                p = Payment()
                p.Id_h = cursor.getInt(0)
                p.Price = cursor.getInt(cursor.getColumnIndex("Price"))
                p.Day_date = cursor.getInt(cursor.getColumnIndex("Day_date"))
                p.Month_date = cursor.getInt(cursor.getColumnIndex("Month_date"))
                p.Year_date = cursor.getInt(cursor.getColumnIndex("Year_date"))

                paymentList.add(p)
            }while (cursor.moveToNext())
        }
        close()
        return paymentList
    }








}