package com.example.ngoctta999123.heath.Retrofit2;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//khoi tao va cau hinh lai cho Retrofit

public class RetrofitClient {
    private static Retrofit retrofit = null;
    public static Retrofit getClient(final String baseurl, String token){
        OkHttpClient builder = new OkHttpClient.Builder()
                                        .addInterceptor(CustomInterceptor.getInstance(token))
                                        .readTimeout(5000, TimeUnit.MILLISECONDS)
                                        .writeTimeout(5000, TimeUnit.MILLISECONDS)
                                        .connectTimeout(10000, TimeUnit.MILLISECONDS)
                                        .retryOnConnectionFailure(true)
                                        .build();
        Gson gson = new GsonBuilder().setLenient().create();
        retrofit = new Retrofit.Builder()
                .baseUrl(baseurl)
                .client(builder)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        return retrofit;
    }
}
