import com.google.gson.annotations.SerializedName

/*
Copyright (c) 2019 Kotlin Data Classes Generated from JSON powered by http://www.json2kotlin.com

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

For support, please feel free to contact me at https://www.linkedin.com/in/syedabsar */


 data class Result_resp (

	@SerializedName("id") val id : Int,
	@SerializedName("mode") val mode : String,
	@SerializedName("status") val status : String,
	@SerializedName("unmappedstatus") val unmappedstatus : String,
	@SerializedName("key") val key : String,
	@SerializedName("txnid") val txnid : Int,
	@SerializedName("transaction_fee") val transaction_fee : Double,
	@SerializedName("amount") val amount : Double,
	@SerializedName("cardCategory") val cardCategory : String,
	@SerializedName("discount") val discount : Double,
	@SerializedName("addedon") val addedon : String,
	@SerializedName("productinfo") val productinfo : String,
	@SerializedName("firstname") val firstname : String,
	@SerializedName("email") val email : String,
	@SerializedName("phone") val phone : Int,
	@SerializedName("udf1") val udf1: String,
	@SerializedName("udf2") val udf2: String,
	@SerializedName("udf3") val udf3: String,
	@SerializedName("udf4") val udf4: String,
	@SerializedName("udf5") val udf5: String,
	@SerializedName("udf6") val udf6: String,
	@SerializedName("udf7") val udf7: String,
	@SerializedName("udf8") val udf8: String,
	@SerializedName("udf9") val udf9: String,
	@SerializedName("udf10") val udf10: String,
	@SerializedName("hash") val hash : String,
	@SerializedName("field1") val field1 : Int,
	@SerializedName("field2") val field2 : Int,
	@SerializedName("field3") val field3 : Int,
	@SerializedName("field4") val field4 : Int,
	@SerializedName("field5") val field5 : Int,
	@SerializedName("field6") val field6 : Int,
	@SerializedName("field7") val field7 : String,
	@SerializedName("field8") val field8 : String,
	@SerializedName("field9") val field9 : String,
	@SerializedName("payment_source") val payment_source : String,
	@SerializedName("PG_TYPE") val pG_TYPE : String,
	@SerializedName("bank_ref_no") val bank_ref_no : Int,
	@SerializedName("ibibo_code") val ibibo_code : String,
	@SerializedName("error_code") val error_code : String,
	@SerializedName("Error_Message") val error_Message : String,
	@SerializedName("name_on_card") val name_on_card : String,
	@SerializedName("card_no") val card_no : String,
	@SerializedName("is_seamless") val is_seamless : Int,
	@SerializedName("surl") val surl : String,
	@SerializedName("furl") val furl : String
)