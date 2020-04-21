package com.myapplication.volly_api.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.myapplication.volly_api.R
import com.myapplication.volly_api.adapter.AdapterRecyclerView
import com.myapplication.volly_api.model.Post
import com.myapplication.volly_api.model.Postdata
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.post1.*
import org.json.JSONArray

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getPost()

    }

    fun getPost(){
        var postList:ArrayList<Post> = ArrayList()
        recyclerview_data.layoutManager = LinearLayoutManager(this@MainActivity)
        var adapterRecycler =AdapterRecyclerView(postList, this)
        // queue all the request by Instantiating
        val queue = Volley.newRequestQueue(this)
        val url:String = "https://jsonplaceholder.typicode.com/posts"
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            Response.Listener { response ->
                var jArr = JSONArray(response)
                for (index in 0 until jArr.length()-1){
                    var jObj = jArr.getJSONObject(index)
                    var id = jObj.getString("id").toInt()
                    var userId = jObj.getString("userId").toInt()
                    var title = jObj.getString("title")
                    var body = jObj.getString("body")
                    postList.add(Post(id, userId, title, body))
                }
                adapterRecycler.setData(postList)
                recyclerview_data.adapter = adapterRecycler
            },
            Response.ErrorListener {
                Toast.makeText(this,"error", Toast.LENGTH_LONG).show()
            })
        queue.add(stringRequest)
    }


//                var strResponse = response.toString()


//                val jsonObj: JSONObject = JSONObject(strResponse)
//                val jsonArray: JSONArray = jsonObj.getJSONArray("")
//                var userid: String = ""
//                var id: String = ""
//                var title: String = ""
//                var body: String = ""
//
//                for (i in 0 until jsonArray.length()) {
//                    var jsonInner: JSONObject = jsonArray.getJSONObject(i)
//                    userid = userid + "\n" + jsonInner.get("userid")
//                    id = id + "\n" + jsonInner.get("id")
//                    title = title + "\n" + jsonInner.get("title")
//                    body = body + "\n" + jsonInner.get("body")
//                }
//
//            },

//
//        que.add(stringRequest)
//    }
}



