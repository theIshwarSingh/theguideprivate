package com.myapplication.newapi.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.myapplication.newapi.ui.viewModel.CatViewModel

abstract class BaseFragment:Fragment(){

    lateinit var viewModel: CatViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.let {
            viewModel = ViewModelProvider(it).get(CatViewModel::class.java)
        }
    }

}