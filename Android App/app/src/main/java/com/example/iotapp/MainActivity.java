package com.example.iotapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.iotapp.client.ApiUtils;
import com.example.iotapp.models.GetStatusRequest;
import com.example.iotapp.models.GetStatusResponse;
import com.example.iotapp.models.UpdateStatusRequest;
import com.example.iotapp.models.UpdateStatusResponse;
import com.example.iotapp.services.ApiAppService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    Boolean lightStatus = false,
            fanStatus = false;
    Integer doorStatus = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ApiAppService mService = ApiUtils.getApiService();
        Button statusButton = (Button) findViewById(R.id.light_button);
        // bật tắt đèn
        Button lightButton = (Button) findViewById(R.id.tgLight);
        Button doorButton = (Button) findViewById(R.id.tgDoor);
        Button fanButton = (Button) findViewById(R.id.tgFan);
        statusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mService.GetStatus().enqueue(new Callback<GetStatusResponse>() {
                    @Override
                    public void onResponse(Call<GetStatusResponse> call, Response<GetStatusResponse> response) {
                        if (response.isSuccessful()) {
                            Boolean fanStatus = response.body().getFanStatus();
                            Boolean lightStatus = response.body().getLightStatus();
                            Integer doorStatus = response.body().getDoorStatus();
                            String message = fanStatus + " || " + lightStatus + " || " + doorStatus;
                            Toast.makeText(MainActivity.this, message , Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(MainActivity.this, "Lỗi gọi Api rồi!" , Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<GetStatusResponse> call, Throwable t) {
                        Toast.makeText(MainActivity.this, "Lỗi rồi!" + t , Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        lightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lightStatus = !lightStatus;
                UpdateStatusRequest data = new UpdateStatusRequest();
                data.setDoorStatus(doorStatus);
                data.setLightStatus(lightStatus);
                data.setFanStatus(fanStatus);
                mService.UpdateStatus(data).enqueue(new Callback<UpdateStatusResponse>() {
                    @Override
                    public void onResponse(Call<UpdateStatusResponse> call, Response<UpdateStatusResponse> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "Thành công!" , Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(MainActivity.this, "Lỗi rồi!" , Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<UpdateStatusResponse> call, Throwable t) {
                        Toast.makeText(MainActivity.this, "Lỗi gọi API rồi!" , Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        doorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (doorStatus == 30){
                    doorStatus = 180;
                } else {
                    doorStatus = 30;
                }
                UpdateStatusRequest data = new UpdateStatusRequest();
                data.setDoorStatus(doorStatus);
                data.setLightStatus(lightStatus);
                data.setFanStatus(fanStatus);
                mService.UpdateStatus(data).enqueue(new Callback<UpdateStatusResponse>() {
                    @Override
                    public void onResponse(Call<UpdateStatusResponse> call, Response<UpdateStatusResponse> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "Thành công!" , Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(MainActivity.this, "Lỗi rồi!" , Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<UpdateStatusResponse> call, Throwable t) {
                        Toast.makeText(MainActivity.this, "Lỗi gọi API rồi!" , Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        fanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fanStatus = !fanStatus;
                UpdateStatusRequest data = new UpdateStatusRequest();
                data.setDoorStatus(doorStatus);
                data.setLightStatus(lightStatus);
                data.setFanStatus(fanStatus);
                mService.UpdateStatus(data).enqueue(new Callback<UpdateStatusResponse>() {
                    @Override
                    public void onResponse(Call<UpdateStatusResponse> call, Response<UpdateStatusResponse> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "Thành công!" , Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(MainActivity.this, "Lỗi rồi!" , Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<UpdateStatusResponse> call, Throwable t) {
                        Toast.makeText(MainActivity.this, "Lỗi gọi API rồi!" , Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

}