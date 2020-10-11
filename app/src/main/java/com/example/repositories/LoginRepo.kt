package com.example.repositories

import APIClient
import APIInterface
import android.widget.Toast
import com.example.models.UserModel
import com.example.models.UserRegisterModel
import com.google.gson.Gson
import com.google.gson.JsonObject
import okhttp3.Request
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginRepo(BaseURL : String) {
    private  var apiInterface : APIInterface? = null
    init {
        if (apiInterface == null) {
            apiInterface = APIClient(BaseURL).client.create(APIInterface::class.java)
        }
    }

    fun registerUser(userName : String, pass: String, email: String,onRegister: (result: UserRegisterModel?) -> Unit) {
        val user = UserModel()
        user.username = userName
        user.password = pass
        user.email = email
        apiInterface?.registerUser(Gson().toJson(user).toString())?.enqueue(object : Callback<UserRegisterModel> {
            override fun onResponse(
                call: Call<UserRegisterModel>,
                response: Response<UserRegisterModel>
            ) {
                if(response.isSuccessful){
                    val responseUser: UserRegisterModel? = response.body()
                    onRegister.invoke(responseUser)
                }else{
                    onRegister.invoke(null)
                }
            }

            override fun onFailure(call: Call<UserRegisterModel>, t: Throwable) {
                onRegister.invoke(null)
            }
        })
    }
}

private fun JsonObject.addProperty(s: String, i: Int, i1: Int, i2: Int) {

}
