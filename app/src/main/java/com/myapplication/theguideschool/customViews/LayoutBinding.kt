package com.myapplication.theguideschool.customViews

import androidx.annotation.LayoutRes

interface LayoutBinding {

    /**
     * Get the layout resource ID for an view that needs to be bound.
     *
     * @return the resource ID of the layout
     */
    @LayoutRes
    fun getLayoutId(): Int
}