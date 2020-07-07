package com.example.ngoctta999123.heath.Retrofit2;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class APIUtils {
    public static final String Base_Url = "https://fathomless-savannah-38522.herokuapp.com/api/";

    //nhan va gui du lieu di
    public static DataClient getData(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences("TOKEN", Context.MODE_PRIVATE);
        String uid = sharedPreferences.getString("ACCESS_TOKEN", "uid");
        Log.d("token",uid);
        return RetrofitClient.getClient(Base_Url, uid).create(DataClient.class);
    }
}
