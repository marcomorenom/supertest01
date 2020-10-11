package com.example.models

import com.google.gson.annotations.SerializedName

class UserRegisterModel {
    @SerializedName("id") var id : String = ""
    @SerializedName("jwt") var jwt : String = ""
}
class UserRegister {
    @SerializedName("username") var username : String = ""
    @SerializedName("password") var password : String = ""
    @SerializedName("email") var email : String = ""
}
class UserLogin {
    @SerializedName("email") var email : String = ""
    @SerializedName("password") var password : String = ""
}