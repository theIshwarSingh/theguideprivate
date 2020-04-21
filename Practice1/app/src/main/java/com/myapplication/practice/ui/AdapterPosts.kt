package com.myapplication.practice.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.myapplication.practice.dataclass.PostDataClass
import androidx.recyclerview.widget.RecyclerView
import com.myapplication.practice.R
import kotlinx.android.synthetic.main.item_posts.view.*


class AdapterPosts(var posts:List<PostDataClass>, var context:Context):  RecyclerView.Adapter<AdapterPosts.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterPosts.ViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val view = layoutInflater.inflate(R.layout.item_posts, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
       return posts.size
    }

    override fun onBindViewHolder(holder: AdapterPosts.ViewHolder, position: Int) {
        val data = posts.get(position)
        holder.postUserId.text = data.userid.toString()
        holder.postId.text = data.id.toString()
        holder.title.text = data.title
        holder.body.text = data.body
    }

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        var postUserId = view.posts_userID
        var postId = view.posts_ID
        var title = view.post_title
        var body = view.post_body
    }

}