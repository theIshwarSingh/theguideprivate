package com.myapplication.prac

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login_.*

class Login_Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_)
       // init()
    }

//    private fun init() {
//        login_btn.setOnClickListener {
//            var login_user_name = login_user_name.text.toString()
//            var login_pwd = login_pwd.text.toString()
//            var user = intent.extras.getString("user")
//            if(login_user_name.equals(user)){
//                Toast.makeText(this,"login done", Toast.LENGTH_SHORT).show()
//
//            }
//            else{
//                Toast.makeText(this,"login fail", Toast.LENGTH_SHORT).show()
//            }
//
//        }

    }

