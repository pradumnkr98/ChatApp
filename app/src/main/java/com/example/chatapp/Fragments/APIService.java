package com.example.chatapp.Fragments;

import com.example.chatapp.Notifications.MyResponse;
import com.example.chatapp.Notifications.Sender;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APIService {

    @Headers(
            {
                    "Content-Type:application/json",
                    "Authorisation:key=AAAAqlCeHtM:APA91bHbyGEaVca4kL3lP6nQWmPEt433byHmDDjoC1bggnUfB5i8OLGyFVZLxBkyWQCdlqlh_AiiIPOcczID-OrdWW9KKqlGMAwejQSisHcJSTX3NIeGcW4uJyK7yuCrIc5bpYcOC1eK"
            }
    )

    @POST("fcm/send")
    Call<MyResponse> sendNotification(@Body Sender body);
}
