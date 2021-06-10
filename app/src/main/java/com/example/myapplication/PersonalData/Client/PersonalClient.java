package com.example.myapplication.PersonalData.Client;

import com.example.myapplication.PersonalData.Model.Example;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public class PersonalClient {
    public static Retrofit retrofit = null;
    public static Vasu_ClientResponse apiInterface;

    public static Vasu_ClientResponse getClient() {

        OkHttpClient okHttpClient = new OkHttpClient.Builder().callTimeout(10, TimeUnit.SECONDS)
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(50, TimeUnit.SECONDS)
                .writeTimeout(50, TimeUnit.SECONDS)
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl("http://161.35.119.227/artwork_cache/api/AdvertiseNewApplications/17/")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiInterface = retrofit.create(Vasu_ClientResponse.class);

        return apiInterface;
    }

    public interface Vasu_ClientResponse {
        @GET("{packageName}")
        Call<Example> getVasuData(@Path("packageName") String packageName);

    }
}
