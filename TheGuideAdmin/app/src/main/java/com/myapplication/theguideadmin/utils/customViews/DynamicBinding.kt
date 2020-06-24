package com.myapplication.theguideschool.customViews

interface DynamicBinding {

    /**
     * Gives the item an opportunity to do work during binding.
     */
    fun bind(holder: DataBoundViewHolder<*>)

}