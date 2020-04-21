package com.myapplication.launch_activity.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.myapplication.launch_activity.R
import kotlinx.android.synthetic.main.activity_main.*

class Activity_three : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        text1.text = "Activity 3"

        btn.setOnClickListener {
            var int = Intent(this, Activity_four::class.java)
            startActivity(int)
        }
    }
}