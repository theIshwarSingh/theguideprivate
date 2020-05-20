package com.myapplication.theguideschool.customViews

/*

 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.BuildConfig
import java.util.*
import kotlin.collections.ArrayList
import com.myapplication.theguideschool.BR

open class MultiTypeDataBoundAdapter(private val mActionCallback: ActionCallback?, vararg items: Any) : BaseDataBoundAdapter<ViewDataBinding>() {

    private val mItems = ArrayList<Any>()

    protected open val items: List<Any>
        get() = mItems

    init {
        Collections.addAll(mItems, *items)
    }

    override fun bindItem(holder: DataBoundViewHolder<ViewDataBinding>, position: Int, payloads: List<Any>) {
        val item = mItems[position]
        holder.binding.setVariable(BR.data, mItems[position])
        // this will work even if the layout does not have a callback parameter
        holder.binding.setVariable(BR.callback, mActionCallback)
        if (item is DynamicBinding) {
            item.bind(holder)
        }
    }

    @LayoutRes
    override fun getItemLayoutId(position: Int): Int {
        // use layout ids as types
        val item = getItem(position)

        if (item is LayoutBinding) {
            return item.getLayoutId()
        }
        if (BuildConfig.DEBUG) {
            throw IllegalArgumentException("unknown item type " + item!!)
        }
        return -1
    }

    override fun getItemCount(): Int {
        return mItems.size
    }

    fun getItem(position: Int): Any? {
        return if (position < mItems.size) mItems[position] else null
    }

    fun indexOf(item: Any): Int {
        return mItems.indexOf(item)
    }

    fun addItem(item: Any) {
        mItems.add(item)
        notifyItemInserted(mItems.size - 1)
    }

    fun addItem(position: Int, item: Any) {
        mItems.add(position, item)
        notifyItemInserted(position)
    }

    fun setItems(vararg items: Any) {
        mItems.clear()
        Collections.addAll(mItems, *items)
        notifyDataSetChanged()
    }

    fun addItems(vararg items: Any) {
        val start = mItems.size
        Collections.addAll(mItems, *items)
        notifyItemRangeChanged(start, items.size)
    }

    fun removeItem(item: Any) {
        val position = mItems.indexOf(item)
        if (position >= 0) {
            mItems.removeAt(position)
            notifyItemRemoved(position)
        }
    }

    fun removeItems(vararg items: Any) {
        val size = mItems.size
        mItems.removeAll(Arrays.asList(*items))
        notifyItemRangeChanged(0, size)
    }

    fun clear() {
        val size = mItems.size
        mItems.clear()
        notifyItemRangeRemoved(0, size)
    }

    /**
     * Class that all action callbacks must extend for the adapter callback.
     */
    interface ActionCallback

}