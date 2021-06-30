package com.example.callapiwithretrofit.api;

import com.example.callapiwithretrofit.model.Account;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    String baseUrl ="https://demo-b5.herokuapp.com/";
    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create();

    ApiService apiService = new Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiService.class);
    @GET("api/accounts")
    Call<List<Account>> getAllAccount(); //@Query("key") String key truyền param

    @POST("api/accounts")
    Call<Account> createAccount(@Body Account account);
}
