package com.example.iotapp.client;

import com.example.iotapp.services.ApiAppService;

public class ApiUtils {
    public static final String BASE_URL = "http://45.119.212.43:5000";

    public static ApiAppService getApiService() {
        return RetrofitClient.getClient(BASE_URL).create(ApiAppService.class);
    }
}
