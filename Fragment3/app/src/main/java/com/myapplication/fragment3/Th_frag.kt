package com.myapplication.fragment3

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [Th_frag.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [Th_frag.newInstance] factory method to
 * create an instance of this fragment.
 */
class Th_frag : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
 // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_th_frag, container, false)

        Toast.makeText(activity, "Th_frag", Toast.LENGTH_LONG).show()
        val fragmentManager = activity?.supportFragmentManager
        val transaction = fragmentManager?.beginTransaction()
         transaction?.addToBackStack(null)

        return view
    }


}     