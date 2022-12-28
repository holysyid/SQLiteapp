package com.example.sqliteapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast


class MainActivity : AppCompatActivity() {

    lateinit var addName : Button
    lateinit var enterName : EditText
    lateinit var enterAge : EditText

    lateinit var txtName : TextView
    lateinit var txtAge : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addName = findViewById(R.id.addName)
        enterName = findViewById(R.id.enterName)
        enterAge = findViewById(R.id.enterAge)

        txtName = findViewById(R.id.Name)
        txtAge = findViewById(R.id.Age)

        addName.setOnClickListener{

            //calling dblhelper
            val db = DBHelper(this, null)

            //get data from editext
            val name = enterName.text.toString()
            val age = enterAge.text.toString()

            //assigning data to AddName
            db.addName(name, age)

            Toast.makeText(this, name + " added to database", Toast.LENGTH_LONG).show()


            enterName.text.clear()
            enterAge.text.clear()
            printName()
        }

    }
    fun printName() {

        val db = DBHelper(this, null)

        val cursor = db.getName()

        cursor!!.moveToFirst()
        var fcolindex = cursor.getColumnIndex(DBHelper.NAME_COl)
        var scolindex = cursor.getColumnIndex(DBHelper.AGE_COL)
        txtName.append(cursor.getString(fcolindex) + "\n")
        txtAge.append(cursor.getString(scolindex) + "\n")


        while(cursor.moveToNext()){
            txtName.append(cursor.getString(fcolindex) + "\n")
            txtAge.append(cursor.getString(scolindex) + "\n")
        }

        // at last we close our cursor
        cursor.close()
    }
}
