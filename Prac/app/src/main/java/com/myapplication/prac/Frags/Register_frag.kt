package com.myapplication.prac.Frags


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.myapplication.prac.R
import kotlinx.android.synthetic.main.fragment_register_frag2.view.*

/**
 * A simple [Fragment] subclass.
 */
class Register_frag : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view:View = inflater.inflate(R.layout.fragment_register_frag2, container, false)

        view.reg_btn.setOnClickListener {

            var reg_user = view.reg_user_name.text.toString()
            var bundle = Bundle()
            bundle.putString("user",reg_user)
            val login = Login_frag()
            login.arguments = bundle
            val fragmanage = activity?.supportFragmentManager
            val trans = fragmanage?.beginTransaction()
            trans?.replace(R.id.frame_frag,login)
            trans?.commit()
        }

      return view
    }


}
