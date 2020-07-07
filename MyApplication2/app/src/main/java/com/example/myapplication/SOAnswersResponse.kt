package com.example.myapplication


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class SOAnswersResponse(
    @SerializedName("message")
    @Expose
    var message: String = "def",
    @SerializedName("userId")
    @Expose
    var userId: String = "",
    @SerializedName("timestamp")
    @Expose
    var timestamp: Long = 0,
    @SerializedName("status")
    @Expose
    var status: Int = 0,
    @SerializedName("error")
    @Expose
    var error: String = "def",
    @SerializedName("path")
    @Expose
    var path: String = "def"
)