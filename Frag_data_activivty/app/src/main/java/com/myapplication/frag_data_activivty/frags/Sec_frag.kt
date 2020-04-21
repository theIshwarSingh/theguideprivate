package com.myapplication.frag_data_activivty.frags

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.myapplication.frag_data_activivty.R
import kotlinx.android.synthetic.main.fragment_sec_frag.view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "DATA"
//private const val ARG_PARAM2 = "param2"


class Sec_frag : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
   // private var param2: String? = null
    private var listener: Fr_frag.OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
          //  param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       var view:View =inflater.inflate(R.layout.fragment_sec_frag, container, false)
        view.msg.text ="$param1"
        return view
    }


}
