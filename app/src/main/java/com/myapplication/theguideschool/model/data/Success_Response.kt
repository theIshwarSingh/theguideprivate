import com.google.gson.annotations.SerializedName


data class Success_Response (

	@SerializedName("status") val status : String,
	@SerializedName("message") val message : String,
	@SerializedName("result") val result : Result,
	@SerializedName("errorCode") val errorCode : String,
	@SerializedName("responseCode") val responseCode : String
)