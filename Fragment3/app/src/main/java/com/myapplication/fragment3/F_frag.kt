package com.myapplication.fragment3

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_f_frag.*
import kotlinx.android.synthetic.main.fragment_f_frag.view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [F_frag.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [F_frag.newInstance] factory method to
 * create an instance of this fragment.
 */
class F_frag : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {



        // Inflate the layout for this fragment
        val view: View =  inflater.inflate(R.layout.fragment_f_frag, container, false)

        Toast.makeText(activity, "F_frag", Toast.LENGTH_LONG).show()
        val secondFragment  = Sec_frag();

        view.sec_frag.setOnClickListener{


            val fragmentManager = activity?.supportFragmentManager
            val transaction = fragmentManager?.beginTransaction()

            transaction?.replace(R.id.fragment_container, secondFragment);
            transaction?.addToBackStack(null)
            transaction?.commit()



        }

        return view
    }

}
