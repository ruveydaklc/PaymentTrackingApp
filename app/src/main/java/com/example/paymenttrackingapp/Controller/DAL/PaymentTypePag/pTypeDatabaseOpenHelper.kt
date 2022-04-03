package com.example.paymenttrackingapp.Controller.DAL.PaymentTypePag

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class pTypeDatabaseOpenHelper(context:Context, databaseName:String, factory: SQLiteDatabase.CursorFactory?, version:Int):
    SQLiteOpenHelper(context,databaseName,factory,version) {


    override fun onCreate(db: SQLiteDatabase) {

        val query = "CREATE TABLE PaymentType(Id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, Title TEXT, Period TEXT, Day TEXT)"
        db.execSQL(query)

    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        /*db.execSQL("DROP TABLE IF EXISTS PaymentType")
        onCreate(db)*/
    }


}