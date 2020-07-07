package com.example.myapplication

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class user(
    @SerializedName("account")
    @Expose
    var account: String = "",
    @SerializedName("password")
    @Expose
    var password: String = ""
)