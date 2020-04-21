package com.myapplication.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init() {
        btn_login.setOnClickListener {

            var username = email_adr.text.toString()
            var password = pwd.text.toString()

            if (username.equals("admin") && password.equals("123") ){

                Toast.makeText(this@MainActivity, "valid ", Toast.LENGTH_SHORT).show()
            }
            else
                Toast.makeText(this@MainActivity, "Invalid Details", Toast.LENGTH_SHORT).show()
        }
    }
}

