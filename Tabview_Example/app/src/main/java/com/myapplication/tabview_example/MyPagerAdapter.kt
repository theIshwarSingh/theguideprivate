package com.myapplication.tabview_example

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.myapplication.tabview_example.frags.FirstFragment
import com.myapplication.tabview_example.frags.Sec_frag
import com.myapplication.tabview_example.frags.Third_frag

class MyPagerAdapter (fm: FragmentManager): FragmentPagerAdapter(fm) {



    override fun getCount(): Int {
       return 3
    }




    override fun getItem(position: Int): Fragment {
       return when (position){
           0->{
               FirstFragment()
           }

           1->{
               Sec_frag()
           }
           else->{
               return Third_frag()
           }
       }
    }


    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0->"First Tab"
            1->"Second Tab"
            else->{
                return "Third Tab"
            }
        }
    }
}