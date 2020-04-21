package com.myapplication.tabview_example.frags


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.myapplication.tabview_example.R
import kotlinx.android.synthetic.main.fragment_common.view.*

/**
 * A simple [Fragment] subclass.
 */
class Third_frag : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view:View =inflater.inflate(R.layout.fragment_common, container, false)

        view.text_view.text ="This is my text from frag3"
        return view
    }


}
