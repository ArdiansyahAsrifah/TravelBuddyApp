package com.example.travelbuddy.Retrofit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class RetrofitClient {
    private static final String BASE_URL = "https://booking-com.p.rapidapi.com/";
    private static final String API_KEY = "ad0ca9bfb3msh9f495b5e281a9dbp11812cjsn7c95d6b04ddd";
    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if (retrofit == null) {
          retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}