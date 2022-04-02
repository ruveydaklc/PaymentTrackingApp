package com.example.paymenttrackingapp.Controller

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class PDatabaseOpenHelper(context: Context, databaseName: String?, factory: SQLiteDatabase.CursorFactory?, version: Int
) : SQLiteOpenHelper(context, databaseName, factory, version) {
    override fun onCreate(db: SQLiteDatabase?) {
        val query= "CREATE TABLE Payment (Id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, Price INTEGER, Day_date INTEGER, Month_date INTEGER, Year_date INTEGER)"
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS Payment")
        onCreate(db)
    }
}