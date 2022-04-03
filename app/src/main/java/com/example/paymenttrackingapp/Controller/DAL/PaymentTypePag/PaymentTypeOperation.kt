package com.example.paymenttrackingapp.Controller.DAL.PaymentTypePag

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.example.paymenttrackingapp.Model.PaymentType

class PaymentTypeOperation(context: Context) {
    var PaymentTypeDatabase: SQLiteDatabase?=null
    val dbOpenHelper: pTypeDatabaseOpenHelper
    val dbName="PaymentTypeDb"
    val tableName="PaymentType"

    init {
        dbOpenHelper= pTypeDatabaseOpenHelper(context,dbName,null,1)
    }

    fun open(){
        PaymentTypeDatabase=dbOpenHelper.writableDatabase
    }

    fun close(){
        if (PaymentTypeDatabase != null && PaymentTypeDatabase!!.isOpen){
            PaymentTypeDatabase!!.close()
        }
    }

    fun getAllPaymentTypes(): Cursor {
        val query = "SELECT * FROM PaymentType"
        return PaymentTypeDatabase!!.rawQuery(query, null)
    }


    @SuppressLint("Range")
    fun getPaymentTypes():ArrayList<PaymentType> {
        val paymentTypeList = ArrayList<PaymentType>()
        var pType: PaymentType
        open()
        var cursor: Cursor = getAllPaymentTypes()
        if (cursor.moveToFirst()) {
            do {
                pType = PaymentType(cursor.getInt(0))
                pType.Title = cursor.getString(cursor.getColumnIndex("Title"))
                pType.Day = cursor.getString(cursor.getColumnIndex("Day"))
                pType.Period =cursor.getString(cursor.getColumnIndex("Period"))

                paymentTypeList.add(pType)
            }while (cursor.moveToNext())
        }
        close()
        return paymentTypeList
    }


    fun addPaymentType(pType:PaymentType):Long{
        val cv= ContentValues()
        cv.put("Title",pType.Title)
        cv.put("Period",pType.Period)
        cv.put("Day",pType.Day)

        open()
        val record = PaymentTypeDatabase!!.insert(tableName,null,cv)
        close()
        return  record

    }

    fun updatePaymentType(pType: PaymentType){
        val cv= ContentValues()
        cv.put("Title",pType.Title)
        cv.put("Period",pType.Period)
        cv.put("Day",pType.Day)

        open()
        PaymentTypeDatabase!!.update(tableName,cv,"Id = ?", arrayOf(pType.Id.toString()))
        close()
    }

    fun deletePaymentType(id:Int){
        open()
        PaymentTypeDatabase!!.delete(tableName,"Id = ?", arrayOf(id.toString()))
        close()
    }



}