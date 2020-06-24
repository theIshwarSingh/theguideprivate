package com.myapplication.newapi.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.myapplication.newapi.R
import com.myapplication.newapi.model.BreedImagesDataClass
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.itemimages.view.*

class ImageAdapter(var context: Context, var list: List<BreedImagesDataClass>):RecyclerView.Adapter<ImageAdapter.ImageAdapterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageAdapterViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.itemimages, parent, false)
        return ImageAdapterViewHolder(view)
    }

    override fun getItemCount(): Int {
      return list.size
    }

    override fun onBindViewHolder(holder: ImageAdapterViewHolder, position: Int) {
        val dataClass = list.get(position)
        Picasso.get().load(dataClass.url).into(holder.imageUrl)

    }

    inner class ImageAdapterViewHolder(view: View):RecyclerView.ViewHolder(view){
        val imageUrl = view.img_cat
    }
}