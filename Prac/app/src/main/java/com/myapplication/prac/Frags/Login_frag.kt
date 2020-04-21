package com.myapplication.prac.Frags


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import com.myapplication.prac.R
import kotlinx.android.synthetic.main.fragment_login_frag.view.*

/**
 * A simple [Fragment] subclass.
 */
class Login_frag : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view:View =  inflater.inflate(R.layout.fragment_login_frag, container, false)
        view.login_btn.setOnClickListener {
        var login_user = view.login_user_name.text.toString()
         var bundle = this.arguments?.getString("user")
        if(login_user.equals(bundle)) {
            Toast.makeText(activity,"Login pass",Toast.LENGTH_SHORT).show()
        }
                else{
            Toast.makeText(activity,"Login fail",Toast.LENGTH_SHORT).show()
        }
            }



    return view
    }


}
