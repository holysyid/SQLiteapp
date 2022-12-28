package com.example.sqliteapp

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context, factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {

    // sqlite query make database
    override fun onCreate(db: SQLiteDatabase) {
        // setting up your query
        val query = ("CREATE TABLE "
                + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY, "
                + NAME_COl + " TEXT,"
                + AGE_COL + " TEXT"
                + ")")

        // execute query
        db.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) {
        // check if table already exists
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME)
        onCreate(db)
    }

    fun addName(name : String, age : String ){

        val values = ContentValues()
        values.put(NAME_COl, name)
        values.put(AGE_COL, age)

        val db = this.writableDatabase

        db.insert(TABLE_NAME, null, values)

        db.close()
    }


    fun getName(): Cursor? {

        val db = this.readableDatabase

        return db.rawQuery("SELECT * FROM " + TABLE_NAME, null) //null is when you dont have any selection filter argumentation like in SQL

    }

    companion object{

        private val DATABASE_NAME = "SQLITETEST"
        private val DATABASE_VERSION = 1
        val TABLE_NAME = "simpletable"

        // column
        val ID_COL = "id"
        val NAME_COl = "name"
        val AGE_COL = "age"
    }
}
