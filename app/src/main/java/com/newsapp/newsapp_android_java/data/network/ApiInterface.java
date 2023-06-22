package com.newsapp.newsapp_android_java.data.network;

import com.newsapp.newsapp_android_java.data.model.TopHeadlineResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {



    //get top-headlines
    @GET("top-headlines")
    Call<TopHeadlineResponse> getHeadlines(@Query("country") String country,@Query("category") String category,@Query("q") String query,@Query("apiKey") String apiKey);



//    //get user data
//    @GET("user-data")
//    Call<UserDataResponse> getUserData(@Header("Authorization") String token);
//
//    //get task info
//    @GET("get-task")
//    Call<TaskResponse> getTaskData(@Header("Authorization") String token, @Query("task_type") int task_type);
//
//
//    //update task info
//    @POST("store-task")
//    Call<UpdateTaskResponse> updateTaskData(@Header("Authorization") String token, @Body UpdateTaskRequest updateTaskRequest);
//
//    //update click info
//    @POST("store-click")
//    Call<ClickResponse> updateClickData(@Header("Authorization") String token, @Body ClickRequest clickRequest);


}
