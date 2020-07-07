package com.example.myapplication

import com.example.retrofit.WebService

object ApiUtils {
    val BASE_URL = "https://fathomless-savannah-38522.herokuapp.com/api/"
    val webService: WebService = RetrofitClient.getClient(BASE_URL).create<WebService>(WebService::class.java!!)
}