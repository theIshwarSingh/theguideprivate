package com.myapplication.newapi.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.myapplication.newapi.R
import com.myapplication.newapi.ui.adapter.ImageAdapter
import kotlinx.android.synthetic.main.fragment_images.view.*

class ImagesFragment : BaseFragment(){

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_images, container, false)
        setupAdapter(view)
        return view
    }

    private fun setupAdapter(view: View) {
        viewModel.imagesLiveData.observe(viewLifecycleOwner, Observer {
            view.recycler_images.adapter = ImageAdapter(this.requireContext(), it)
        })
       viewModel.getImages()

    }

}