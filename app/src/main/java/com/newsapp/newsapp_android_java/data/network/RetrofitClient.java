package com.newsapp.newsapp_android_java.data.network;




import com.newsapp.newsapp_android_java.utils.Constants;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static Retrofit retrofit;
    private static final String Base_Url= Constants.BASE_URL;


    public static Retrofit getRetrofitInstance(){
        if (retrofit==null){
            retrofit=new Retrofit.Builder()
                    .baseUrl(Base_Url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
