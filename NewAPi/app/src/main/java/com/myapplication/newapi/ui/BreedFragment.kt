package com.myapplication.newapi.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.myapplication.newapi.R
import com.myapplication.newapi.ui.adapter.BreedAdapter
import com.myapplication.newapi.ui.viewModel.CatViewModel
import kotlinx.android.synthetic.main.fragment_breed.*
import kotlinx.android.synthetic.main.fragment_breed.view.*

class BreedFragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_breed, container, false)
        setupAdapter(view)
        return view
    }

    private fun setupAdapter(view: View) {
        viewModel.breedsLiveData.observe(viewLifecycleOwner, Observer {
            view.recycler_breeds.adapter = BreedAdapter(this.requireContext(),it)
        })
        viewModel.getCatRepository()
    }

}