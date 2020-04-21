package com.myapplication.launch_activity.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.myapplication.launch_activity.MainActivity
import com.myapplication.launch_activity.R
import kotlinx.android.synthetic.main.activity_main.*

class Activity_four : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        text1.text = "Activity 4"

        btn.setOnClickListener {
            var int = Intent( this, Activity_two::class.java)
            startActivity(int)

    }
    }
}
