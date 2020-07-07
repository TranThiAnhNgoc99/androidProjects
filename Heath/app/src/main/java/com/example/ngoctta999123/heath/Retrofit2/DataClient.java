package com.example.ngoctta999123.heath.Retrofit2;

import com.example.ngoctta999123.heath.models.Doctor;
import com.example.ngoctta999123.heath.models.inquiries.CreateInquiriRequest;
import com.example.ngoctta999123.heath.models.inquiries.FormTV;
import com.example.ngoctta999123.heath.models.Image;
import com.example.ngoctta999123.heath.models.Login.LoginModel;
import com.example.ngoctta999123.heath.models.profile.UpdateInfo;
import com.example.ngoctta999123.heath.models.User;
import com.example.ngoctta999123.heath.models.inquiries.Inquiries;
import com.example.ngoctta999123.heath.models.records.Inquiry;
import com.example.ngoctta999123.heath.models.records.Record;
import com.example.ngoctta999123.heath.models.records.Records;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface DataClient {
    @POST("auth/login")
    Call<String> loginData(@Body LoginModel loginModel);

    @POST("auth/sign_out")
    Call<String> logOut();

    @FormUrlEncoded
    @GET("inquiries")
    Call<List<FormTV>> getFormTV(@Path("id") String id);

    @GET("user")
    Call<User> getUserInfo();

    @PUT("user")
    Call<User> updateInfo(@Body UpdateInfo updateInfo);

    @GET("inquiries")
    Call<Inquiries> getInquiries(@Query("page") int page, @Query("size") int size);

    @POST("inquiries")
    Call<Inquiry> createInquiries(@Body CreateInquiriRequest createInquiriRequest);

    @GET("records")
    Call<Records> getRecords(@Query("page") String page, @Query("size") String size, @Query("type") int type);

    @GET("records/{id}")
    Call<Record> getRecordDetail(@Path("id") int id, @Query("type") int type);

    @GET("doctors/{id}")
    Call<Doctor> getDoctorInfo(@Query("id") String id);

    @Multipart
    @POST("images")
    Call<Image> uploadPhoto(@Part List<MultipartBody.Part> images);

    @Multipart
    @POST("images/avatar")
    Call<String> uploadAvatar(@Part List<MultipartBody.Part> images);
}
