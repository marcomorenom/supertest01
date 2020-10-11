package com.example.repositories

import APIClient
import APIInterface
import com.example.models.UserLogin
import com.example.models.UserRegister
import com.example.models.UserRegisterModel
import com.google.gson.Gson
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
        val user = UserRegister()
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
    fun login(email : String, pass: String, onRegister: (result: UserRegisterModel?) -> Unit) {
        val user = UserLogin()
        user.email = email
        user.password = pass
        apiInterface?.login(Gson().toJson(user).toString())?.enqueue(object : Callback<UserRegisterModel> {
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