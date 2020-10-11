import com.example.models.UserRegister
import com.example.models.UserRegisterModel
import com.google.gson.GsonBuilder

import java.util.concurrent.TimeUnit

import okhttp3.OkHttpClient
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.*


class APIClient(private val BASE_URL: String) {

    private lateinit var retrofit: Retrofit

    val client: Retrofit
        get() {
            val client = OkHttpClient.Builder()
                .connectTimeout(1, TimeUnit.MINUTES)
                .retryOnConnectionFailure(true)
                .build()
            val gson = GsonBuilder()
                .setLenient()
                .create()
            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build()

            return this.retrofit

        }

}



interface APIInterface {
    @POST("users")
    @Headers("Content-Type: application/json")
    abstract fun registerUser(@Body requestJsonString: String): Call<UserRegisterModel>
    @POST("login")
    @Headers("Content-Type: application/json")
    abstract fun login(@Body requestJsonString: String): Call<UserRegisterModel>
    @GET("users")
    abstract fun getUsers(): Call<Response<ArrayList<UserRegister>>>
    @GET("users")
    abstract fun getUsersById(@Query("id") userID: String): Call<Response<UserRegister>>
}