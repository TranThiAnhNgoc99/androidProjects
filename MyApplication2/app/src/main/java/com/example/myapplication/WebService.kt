package com.example.retrofit

import com.example.myapplication.SOAnswersResponse
import com.example.myapplication.user
import retrofit2.Call
import retrofit2.http.*


interface WebService {
    @POST("auth/login")
    fun getAnswers(@Body mU: user, @Header("Client-Type") h:String): Call<SOAnswersResponse>
//    @GET("/answers?order=desc&sort=activity&site=stackoverflow")
//    fun getAnswers(@Query("tagged") tags: String): Call<List<SOAnswersResponse>>
}