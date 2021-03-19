package com.example.redmoonstudio;


import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface GetDataService {

    @FormUrlEncoded
    @POST("/Android_login.php")

    Call<String>  login(
            @Field("Email") String Email,
            @Field("Password") String Password
//            @Field("Token") String Token
    );



    @FormUrlEncoded
    @POST("/android_addEvent.php")
    Call<String> book(
            @Field("Name") String F_Name,
            @Field("Venue") String Venue,
            @Field("Date") String Date,
            @Field("Time") String Time,
            @Field("Camera") String Camera,
            @Field("Contact") String cell,
            @Field("Link") String Link,
            @Field("Price") String Price,
            @Field("P_name") String P_name
    );

    @POST("/android_viewPhotographer.php")
    Call<String>  Photographer(
    );

    @FormUrlEncoded
    @POST("/android_viewEvent.php")
    Call<String>  Event(
            @Field("Date") String Date
            );

    @FormUrlEncoded
    @POST("/android_report.php")
    Call<String>  Report(
            @Field("Date") String Date
    );

}

