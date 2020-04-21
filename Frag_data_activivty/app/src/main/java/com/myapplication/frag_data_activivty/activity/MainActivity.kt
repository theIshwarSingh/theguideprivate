package com.myapplication.frag_data_activivty.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.myapplication.frag_data_activivty.R
import com.myapplication.frag_data_activivty.frags.Fr_frag
import com.myapplication.frag_data_activivty.frags.Sec_frag
import kotlinx.android.synthetic.main.fragment_fr_frag.*

class MainActivity : AppCompatActivity(), Fr_frag.OnFragmentInteractionListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }



    public fun init(){

        var firstFragment = Fr_frag()
        var fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.frag_frame, firstFragment)
        fragmentTransaction.commit()


    }




    override fun onFragmentInteraction(uri: String) {
        Toast.makeText(this, uri , Toast.LENGTH_SHORT).show()
        var secondFragment = Sec_frag()
        var bundle = Bundle()
        bundle.putString("DATA", uri)
        secondFragment.arguments = bundle


        var fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frag_frame2, secondFragment)
        fragmentTransaction.commit()
    }



}
