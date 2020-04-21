package com.myapplication.project1_ecom.helper

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.JsonObject
import com.myapplication.project1_ecom.R
import com.myapplication.project1_ecom.R.id.recyclerview_category
import com.myapplication.project1_ecom.activity.CommonActivity
import com.myapplication.project1_ecom.activity.HomeActivity
import com.myapplication.project1_ecom.adapters.AdapterCategory
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_home.view.*
import org.json.JSONArray
import org.json.JSONObject
import com.myapplication.project1_ecom.activity.MainActivity
import com.myapplication.project1_ecom.adapters.AdapterOrderHistory
import com.myapplication.project1_ecom.adapters.AdapterProducts
import com.myapplication.project1_ecom.adapters.AdapterSubCategory
import com.myapplication.project1_ecom.model.*


class APIConnection constructor(var context: Context){

    val queue = Volley.newRequestQueue(this.context)

    fun shop_reg(fname:String, lname:String, address:String, email:String, mobile:String, password:String){
        val url:String = "http://rjtmobile.com/aamir/e-commerce/android-app/shop_reg.php?fname=$fname&lname=$lname&address=$address&email=$email&mobile=$mobile&password=$password"
        Log.d("response", url)
        val stringRequest = StringRequest(
            Request.Method.POST, url,
            Response.Listener { response ->
                if(response.toString() == "successfully registered"){
                     Toast.makeText(this.context, "User Registered", Toast.LENGTH_LONG).show()
                }else
                {
                    Toast.makeText(this.context, response.toString(), Toast.LENGTH_LONG).show()
                }

            },  Response.ErrorListener {e->
                Log.d("response", e.message)
                Toast.makeText(this.context, e.message , Toast.LENGTH_LONG).show()
            })
        queue.add(stringRequest)
    }


    fun shop_login(mobile:String, password: String, context: Context):Boolean{
        val url:String = "http://rjtmobile.com/aamir/e-commerce/android-app/shop_login.php?mobile=$mobile&password=$password"
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            Response.Listener { response ->
                Log.d("response1", response)
                var jArry= JSONArray(response)
                for(index in 0 until jArry.length()){
                    var jObj = jArry.getJSONObject(index)
                    var sucess = jObj.getString("msg")
                    if (sucess == "success"){
                        var id = jObj.getString("id")
                        var fname = jObj.getString("firstname")
                        var lname = jObj.getString("lastname")
                        var email = jObj.getString("email")
                        var address = "Chicago"
                        var mobile = jObj.getString("mobile")
                        var api_key = jObj.getString("appapikey ")
                        var session = SessionManagement(this.context)
                        session.register(id,fname,lname,email,address,mobile,api_key)
                        var intent = Intent(context, HomeActivity::class.java)
                        context.startActivity(intent)
                    }else {
                        Toast.makeText(this.context,"Mobile Number Not Registered", Toast.LENGTH_LONG).show()
                    }
                }
            },  Response.ErrorListener {e->
                Log.d("response1", e.message)
               // Toast.makeText(this.context,"Something is wrong", Toast.LENGTH_LONG).show()
            })
        queue.add(stringRequest)
        return true
    }


    fun cust_category( context: Context,recyclerView: RecyclerView) {
        var categorylist:ArrayList<CategoryDataClass> = ArrayList()
        var session = SessionManagement(this.context)
        var userid = session.sharedPreferences.getString("id","")
        var apikey=  session.sharedPreferences.getString("apikey", "")
        val url: String = "http://rjtmobile.com/ansari/shopingcart/androidapp/cust_category.php?api_key=$apikey&user_id=$userid"
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            Response.Listener { response ->
                Log.d("response1", response)
                var jsonString = response.toString()
                var jsonObj = JSONObject(jsonString)
                var jarry= jsonObj.optJSONArray("category")
                for(index in 0 until jarry.length()){
                    var jobj = jarry.getJSONObject(index)
                    var cid = jobj.getString("cid").toInt()
                    var cname = jobj.getString("cname")
                    var cdiscription = jobj.getString("cdiscription")
                    var cimage = jobj.getString("cimagerl")
                    categorylist.add(CategoryDataClass(cid,cname,cdiscription,cimage))

                }
                val horizontalLayoutManagaer =
                    LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                recyclerView.layoutManager = horizontalLayoutManagaer
               // recyclerView.bringToFront()
                recyclerView.adapter = AdapterCategory(categorylist,context)
            },  Response.ErrorListener {e->
                Log.d("response1", e.message)
                // Toast.makeText(this.context,"Something is wrong", Toast.LENGTH_LONG).show()
            })
        queue.add(stringRequest)
    }



    fun cust_sub_category (cid:Int, context: Context, recyclerView: RecyclerView ){
        var subcategorylist:ArrayList<SubCategoryDataClass> = ArrayList()
        var session = SessionManagement(this.context)
        var userid = session.sharedPreferences.getString("id","")
        var apikey=  session.sharedPreferences.getString("apikey", "")
        val url: String = "http://rjtmobile.com/ansari/shopingcart/androidapp/cust_sub_category.php?Id=$cid&api_key=$apikey&user_id=$userid"
        Log.d("apicall",url)
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            Response.Listener { response ->
                Log.d("response2", response)
                var jsonString = response.toString()
                var jsonObj = JSONObject(jsonString)
                var jarry= jsonObj.optJSONArray("subcategory")
                for(index in 0 until jarry.length()){
                    var jobj = jarry.getJSONObject(index)
                    var cid = jobj.getString("scid").toInt()
                    var cname = jobj.getString("scname")
                    var cdiscription = jobj.getString("scdiscription")
                    var cimage = jobj.getString("scimageurl")
                    subcategorylist.add(SubCategoryDataClass(cid,cname,cdiscription,cimage))

                }
                recyclerView.apply {
                    layoutManager = LinearLayoutManager(context)
                    // Access the RecyclerView Adapter and load the data into it
                    adapter = AdapterSubCategory(subcategorylist,context)
                }
            },  Response.ErrorListener {e->
                Log.d("response1", e.message)
                // Toast.makeText(this.context,"Something is wrong", Toast.LENGTH_LONG).show()
            })
        queue.add(stringRequest)


    }


    fun product_details(cid:Int, scid:Int, context: Context, recyclerView: RecyclerView){
        var productlist:ArrayList<ProductDataClass> = ArrayList()
        var session = SessionManagement(this.context)
        var userid = session.sharedPreferences.getString("id","")
        var apikey=  session.sharedPreferences.getString("apikey", "")
        val url: String = "http://rjtmobile.com/ansari/shopingcart/androidapp/product_details.php?cid=$cid&scid=$scid&api_key=$apikey&user_id=$userid"
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            Response.Listener { response ->
                Log.d("productlist", response)
                var jsonString = response.toString()
                var jsonObj = JSONObject(jsonString)
                var jarry= jsonObj.optJSONArray("products")
                for(index in 0 until jarry.length()){
                    var jobj = jarry.getJSONObject(index)
                    var id = jobj.getString("id").toInt()
                    var pname = jobj.getString("pname")
                    var quanitity = jobj.getString("quantity").toInt()
                    var prize = jobj.getString("prize").toInt()
                    var discription = jobj.getString("discription")
                    var image = jobj.getString("image")
                   productlist.add(ProductDataClass(id,pname,quanitity,prize,discription,image))

                }
                recyclerView.apply {
                    layoutManager = LinearLayoutManager(context)
                    // Access the RecyclerView Adapter and load the data into it
                    adapter = AdapterProducts(productlist, context)
                }
            },  Response.ErrorListener {e->
                Log.d("response1", e.message)
                // Toast.makeText(this.context,"Something is wrong", Toast.LENGTH_LONG).show()
            })
        queue.add(stringRequest)


    }


    fun order_history(context: Context, recyclerView: RecyclerView){
        var orderlist:ArrayList<OrderHistoryDataclass> = ArrayList()
        var session = SessionManagement(this.context)
        var userid = session.sharedPreferences.getString("id","")
        var apikey=  session.sharedPreferences.getString("apikey", "")
        var mobile_no=  session.sharedPreferences.getString("mobile_no", "")
        val url: String = "http://rjtmobile.com/aamir/e-commerce/android-app/order_history.php?api_key=$apikey&user_id=$userid&mobile=$mobile_no"
        Log.d("orderhist", url)
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            Response.Listener { response ->
                Log.d("orderlist", response)
                var jsonString = response.toString()
                var jsonObj = JSONObject(jsonString)
                var jarry= jsonObj.optJSONArray("Order history")
                for(index in 0 until jarry.length()){
                    var jobj = jarry.getJSONObject(index)
                    var orderid = jobj.getString("orderid")
                    var orderstatus = jobj.getString("orderstatus")
                    var name = jobj.getString("name")
                    var billingadd = jobj.getString("billingadd")
                    var deliveryadd= jobj.getString("deliveryadd")
                    var mobile = jobj.getString("mobile")
                    var email = jobj.getString("email")
                    var itemid = jobj.getString("itemid")
                    var itemname = jobj.getString("itemname")
                    var itemquanitity = jobj.getString("itemquantity")
                    var totalprice = jobj.getString("totalprice")
                    var paidprice = jobj.getString("paidprice")
                    var placedon=jobj.getString("placedon")

                    orderlist.add(OrderHistoryDataclass(orderid,orderstatus,name,billingadd
                    ,deliveryadd,mobile,email,itemid,itemname,itemquanitity,totalprice,paidprice,placedon))

                }
                recyclerView.apply {
                    layoutManager = LinearLayoutManager(context)
                    // Access the RecyclerView Adapter and load the data into it
                    adapter = AdapterOrderHistory(orderlist, context)
                }
            },  Response.ErrorListener {e->
                Log.d("response1", e.message)
                // Toast.makeText(this.context,"Something is wrong", Toast.LENGTH_LONG).show()
            })
        queue.add(stringRequest)


    }


    fun order_place(itemid:Int, item_name:String, item_quantity:Int, item_price:String, context:Context){
        var session = SessionManagement(this.context)
        var userid = session.sharedPreferences.getString("id","")
        var apikey=  session.sharedPreferences.getString("apikey", "")
        var adr = session.sharedPreferences.getString("address","")
        var mobile_no=  session.sharedPreferences.getString("mobile_no", "")
        val url: String = "http://rjtmobile.com/aamir/e-commerce/android-app/orders.php?item_id=$itemid&" +
                "item_names=$item_name&item_quantity=$item_quantity&final_price=$item_price&api_key=$apikey&user_id=$userid&user_name=" +
                "Aamir&billingadd=$adr&deliveryadd=$adr&" +
                "mobile=$mobile_no&email=aa@gmail.com"
        Log.d("orderplace", url)
        val stringRequest = StringRequest(
            Request.Method.POST, url,
            Response.Listener { response ->
                if(response.toString() == "successfully registered"){
                    Toast.makeText(this.context, "Order Confirmed", Toast.LENGTH_LONG).show()
                }else
                {
                }

            },  Response.ErrorListener {e->
            })
        queue.add(stringRequest)
    }



    fun changepass(old_pass:String, new_pass:String){
        var session = SessionManagement(this.context)
        var mobile_no=  session.sharedPreferences.getString("mobile_no", "")
        val url:String = "http://rjtmobile.com/aamir/e-commerce/android-app/shop_reset_pass.php?&mobile=$mobile_no&password=$old_pass&newpassword=$new_pass"
        Log.d("response", url)
        val stringRequest = StringRequest(
            Request.Method.POST, url,
            Response.Listener { response ->
                Toast.makeText(this.context, "Password Changed", Toast.LENGTH_LONG).show()
                session.sharedPreferences.edit().putString("pwd",new_pass)

            },  Response.ErrorListener {e->
                Log.d("response", e.message)
            })
        queue.add(stringRequest)
    }

    fun changeaccountdetails(fname:String, lname:String, email:String, adr:String, mobile:String){
        var session = SessionManagement(this.context)
        val url:String = "http://rjtmobile.com/aamir/e-commerce/android-app/edit_profile.php?fname=$fname&lname=$lname&address=$adr& email=$email&mobile=$mobile"
        Log.d("response", url)
        val stringRequest = StringRequest(
            Request.Method.POST, url,
            Response.Listener { response ->
                Toast.makeText(this.context, "Account Updated", Toast.LENGTH_LONG).show()
                session.sharedPreferences.edit().putString("firstname",fname)
                session.sharedPreferences.edit().putString("lastname",lname)
                session.sharedPreferences.edit().putString("email",email)
                session.sharedPreferences.edit().putString("address",adr)
                session.sharedPreferences.edit().putString("mobile_no",mobile)
            },  Response.ErrorListener {e->
                Log.d("response", e.message)
                Toast.makeText(this.context, e.message , Toast.LENGTH_LONG).show()
            })
        queue.add(stringRequest)
    }
}
