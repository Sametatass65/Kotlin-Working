package com.smt.interfaceapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        framelayout3.setOnClickListener{
            framelayout2.visibility = View.VISIBLE
            framelayout3.visibility = View.INVISIBLE
            framelayout1.visibility = View.INVISIBLE
        }
        framelayout2.setOnClickListener{
            framelayout2.visibility = View.INVISIBLE
            framelayout3.visibility = View.INVISIBLE
            framelayout1.visibility = View.VISIBLE
        }
        framelayout1.setOnClickListener{
            framelayout2.visibility = View.INVISIBLE
            framelayout3.visibility = View.VISIBLE
            framelayout1.visibility = View.INVISIBLE
        }




    }
}