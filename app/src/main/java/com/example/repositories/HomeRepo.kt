package com.example.repositories

import APIClient
import APIInterface
import com.example.models.*
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HomeRepo(BaseURL : String) {
    private  var apiInterface : APIInterface? = null
    init {
        if (apiInterface == null) {
            apiInterface = APIClient(BaseURL).client.create(APIInterface::class.java)
        }
    }

    fun getUserList(onRespose: (result: ArrayList<UserItem>?) -> Unit) {
        apiInterface?.getUsers()?.enqueue(object :
            Callback<UserListResponse> {
            override fun onResponse(
                call: Call<UserListResponse>,
                response : Response<UserListResponse>
            ) {
                if(response.isSuccessful){
                    val content: UserListResponse? = response.body()
                    val responseArray: ArrayList<UserItem>? = content?.resources
                    onRespose.invoke(responseArray)
                }else{
                    onRespose.invoke(null)
                }
            }

            override fun onFailure(call: Call<UserListResponse>, t: Throwable) {
                onRespose.invoke(null)
            }
        })
    }
}