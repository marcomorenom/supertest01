package com.example.repositories

import APIClient
import APIInterface
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

    private fun registerUser(onRegister: (result: Boolean) -> Unit) {
        apiInterface?.getUsers()?.enqueue(object : Callback<Response<String>> {
            override fun onResponse(
                call: Call<Response<String>>,
                response: Response<Response<String>>
            ) {
                onRegister.invoke(true)
            }

            override fun onFailure(call: Call<Response<String>>, t: Throwable) {
                onRegister.invoke(false)
            }

        })
    }
}