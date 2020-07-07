package com.example.myapplication

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {
    var uid:String = ""
    var pass:String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("Test","Create")
    }



    fun onPost(view: View) {
        uid = edt_uid.text.toString()
        pass = edt_pass.text.toString()
        Log.d("Test","Post")

        var acc: user = user()
        acc.account = uid
        acc.password = pass


        val mService = ApiUtils.webService
        mService.getAnswers(acc,"a")
            .enqueue(object : Callback<SOAnswersResponse> {
                override fun onResponse(call: Call<SOAnswersResponse>, response: Response<SOAnswersResponse>) {
                    val statusCode = response.code()
                    Log.d("MainActivity", statusCode.toString())
                    if (response.isSuccessful()) {
                        if (statusCode == 200){
                            txt_res.setText("Đăng nhập thành công")
                        }
                    } else {
                        if ( statusCode == 404 ){
                            txt_res.setText("Đăng nhập thất bại")
                        }
                        if (statusCode == 400 ){
                            txt_res.setText("Thiếu Header Client-Type")
                        }
                    }
                }

                override fun onFailure(call: Call<SOAnswersResponse>, t: Throwable) {
                    Log.d("MainActivity", "error loading from API")
                }
            })

    }
}