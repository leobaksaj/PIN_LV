package com.example.lv1_baksaj;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiManager {

    static ApiManager instanca;
    private CourseApiService service;

    private ApiManager(){
        Retrofit.Builder builder = new Retrofit.Builder();
        //postavljanje retorfita
        Retrofit retrofit = builder.baseUrl("https://catalog-api.udacity.com/").addConverterFactory(GsonConverterFactory.create()).build();
        service = retrofit.create(CourseApiService.class);
    }
    public static ApiManager getInstanca(){
        if (instanca == null){
            instanca = new ApiManager();
        }
        return  instanca;
    }

    public CourseApiService service(){
        return service;
    }
}
