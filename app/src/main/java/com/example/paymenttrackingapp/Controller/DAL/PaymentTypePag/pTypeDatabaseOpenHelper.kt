package com.example.paymenttrackingapp.Controller.DAL.PaymentTypePag

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.FileObserver.CREATE
import java.text.Collator.PRIMARY

class pTypeDatabaseOpenHelper(context:Context, databaseName:String, factory: SQLiteDatabase.CursorFactory?, version:Int):
    SQLiteOpenHelper(context,databaseName,factory,version) {


    override fun onCreate(db: SQLiteDatabase) {


        val paymentTypeTable = "CREATE TABLE PaymentType(Id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, Title TEXT, Period TEXT, Day TEXT)"

        val paymentTable ="CREATE TABLE Payment(Id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,Price INTEGER, ptTitle TEXT, Day_date INTEGER, Month_date INTEGER, Year_date INTEGER)"


        db.execSQL(paymentTable)
        db.execSQL(paymentTypeTable)

    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS PaymentType")
        db.execSQL("DROP TABLE IF EXISTS Payment")
        onCreate(db)
    }


}