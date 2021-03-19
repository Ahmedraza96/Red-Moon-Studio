package com.example.redmoonstudio;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface GetDataServiceAdd {


    @FormUrlEncoded
    @POST("/android_photographer.php")
    Call<String> add(
            @Field("F_Name") String F_Name,
            @Field("L_Name") String L_Name,
            @Field("gender") String gender,
            @Field("age") String age,
            @Field("address") String address,
            @Field("cell") String cell,
            @Field("experience") String experience
    );


}
