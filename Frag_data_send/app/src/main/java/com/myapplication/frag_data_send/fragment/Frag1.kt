package com.myapplication.frag_data_send.fragment

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.myapplication.frag_data_send.R
import kotlinx.android.synthetic.main.fragment_frag1.*
import kotlinx.android.synthetic.main.fragment_frag1.view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "no"

var sec_frag = Sec_Fragment()
//private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [Frag1.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [Frag1.newInstance] factory method to
 * create an instance of this fragment.
 */
class Frag1 : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
   // private var listener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            //param2 = it.getString(ARG_PARAM2)



        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view: View = inflater.inflate(R.layout.fragment_frag1, container, false)
        //Toast.makeText(activity, param1 , Toast.LENGTH_LONG).show()

        view.btn.setOnClickListener {
            var text1 = text1.text.toString()
            var bundle = Bundle()
            bundle.putString("no", text1)
            sec_frag.arguments = bundle


            val fragmentManager = activity?.supportFragmentManager
            val transaction = fragmentManager?.beginTransaction()

            transaction?.replace(
                R.id.frag_frame2,
                sec_frag
            );
            transaction?.commit()


        }
        return view

    }




        // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
     //   listener?.onFragmentInteraction(uri)
    }

//    override fun onAttach(context: Context) {
//        super.onAttach(context)
//        if (context is OnFragmentInteractionListener) {
//            listener = context
//        } else {
//            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
//        }
//    }
//
//    override fun onDetach() {
//        super.onDetach()
//        listener = null
//    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
//     */
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Frag1.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Frag1().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                   // putString(ARG_PARAM2, param2)
                }
            }
    }
}
