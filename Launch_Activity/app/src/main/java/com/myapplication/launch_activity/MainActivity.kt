package com.myapplication.launch_activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.myapplication.launch_activity.activities.Activity_two
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        text1.text = "1"

        btn.setOnClickListener {
            var int = Intent( this, Activity_two::class.java)
            startActivity(int)
        }

    }
}
