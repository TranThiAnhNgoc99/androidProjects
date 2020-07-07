package com.example.myapplication

import retrofit2.Call
import retrofit2.http.*

interface OurRetrofit {

    @POST("auth/login")
    fun PostData(@Body mUser: user, @Header("Client-Type") header:String) : Call<user>
//    fun PostData(@Field("account") mUser: String, @Field("password") mPass: String, @Header("Client-Type") header:String) : Call<res>

}
