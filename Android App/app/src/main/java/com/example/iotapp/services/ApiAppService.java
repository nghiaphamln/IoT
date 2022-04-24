package com.example.iotapp.services;

import com.example.iotapp.models.GetStatusRequest;
import com.example.iotapp.models.GetStatusResponse;
import com.example.iotapp.models.UpdateStatusRequest;
import com.example.iotapp.models.UpdateStatusResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiAppService {

    @GET("/GetStatus")
    Call<GetStatusResponse> GetStatus();

    @Headers("AuthenToken: Bearer f30365f278cd4c67ab0c5836666b378a1e666f0c6442e474f9d99004aeab245e")
    @POST("/UpdateStatus")
    Call<UpdateStatusResponse> UpdateStatus(@Body UpdateStatusRequest data);
}
