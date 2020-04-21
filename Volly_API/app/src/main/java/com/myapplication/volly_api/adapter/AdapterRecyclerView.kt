package com.myapplication.volly_api.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.myapplication.volly_api.R
import com.myapplication.volly_api.model.Post
import kotlinx.android.synthetic.main.data_list.view.*

class AdapterRecyclerView(var list:ArrayList<Post>, var context: Context) : RecyclerView.Adapter<AdapterRecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val view = layoutInflater.inflate(R.layout.data_list, parent, false)
        return ViewHolder(view)


    }

    override fun getItemCount(): Int {
    return list.size
    }

    override fun onBindViewHolder(holder: AdapterRecyclerView.ViewHolder, position: Int) {
      var data = list.get(position)
        holder.userid.text = data.userId.toString()
        holder.id.text = data.id.toString()
        holder.title.text = data.title
        holder.body.text = data.body

    }


    fun setData(list:ArrayList<Post>){
        this.list = list
      //  notifyDataSetChanged()
    }



    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view){



            var userid =  view.userid
            var id = view.id_value
            var title = view.data_title
            var body = view.data_body
        }
    }
