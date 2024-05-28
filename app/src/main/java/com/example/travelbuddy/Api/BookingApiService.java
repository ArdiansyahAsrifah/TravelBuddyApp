package com.example.travelbuddy.Api;

import com.example.travelbuddy.Retrofit.HotelResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface BookingApiService {

    String RAPID_API_KEY = "ad0ca9bfb3msh9f495b5e281a9dbp11812cjsn7c95d6b04ddd";

    String RAPID_API_HOST = "booking-com.p.rapidapi.com";

    @Headers({
            "X-RapidAPI-Key: " + RAPID_API_KEY,
            "X-RapidAPI-Host: " + RAPID_API_HOST
    })

    @GET("/v1/static/hotels")
    Call<HotelResponse> getHotels(@Query("page") int page);
}
