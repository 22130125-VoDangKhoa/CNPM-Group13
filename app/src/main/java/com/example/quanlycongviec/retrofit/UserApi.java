package com.example.quanlycongviec.retrofit;

import com.example.quanlycongviec.database.model.User;  // nhớ tạo class User trên Android tương ứng

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserApi {

    @GET("/api/users")
    Call<List<User>> getAllUsers();

    @GET("/api/users/{email}")
    Call<User> getUserByEmail(@Path("email") String email);

    @POST("/api/users")
    Call<Void> createUser(@Body User user);
}
