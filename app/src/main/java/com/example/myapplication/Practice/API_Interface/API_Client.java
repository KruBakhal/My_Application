package com.example.myapplication.Practice.API_Interface;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.myapplication.Constants.WebConstants.BASE_URL;

/**
 * Singelton class
 * base inttialize in application class
 */
public class API_Client {


    public static Retrofit retrofit;
    public static API_Interface api_interface;

    public static API_Interface getInstanceAPI() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            api_interface = retrofit.create(API_Interface.class);
        }
        return api_interface;

    }

    /*public static API_Interface getInstanceAPI() {
        return getInstanceAPI().create(API_Interface.class);
    }*/

}
