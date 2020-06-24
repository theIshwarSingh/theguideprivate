package com.myapplication.newapi.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.myapplication.newapi.R
import com.myapplication.newapi.model.BreedsDataClass
import kotlinx.android.synthetic.main.itembreeds.view.*

class BreedAdapter(var context: Context, var list: List<BreedsDataClass>) :
    RecyclerView.Adapter<BreedAdapter.BreedsViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BreedAdapter.BreedsViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.itembreeds, parent, false)
        return BreedsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: BreedAdapter.BreedsViewHolder, position: Int) {
        val dataClass = list.get(position)
        holder.breedName.text = dataClass.name
    }

    inner class BreedsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val breedName = itemView.tv_breedName

    }
}