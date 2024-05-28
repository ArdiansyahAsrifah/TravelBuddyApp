package com.example.travelbuddy.Retrofit;

import com.example.travelbuddy.Models.Hotel;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class HotelResponse {
    @SerializedName("result")
    private List<Hotel> hotels;

    public List<Hotel> getHotels() {
        return hotels;
    }

    public void setHotels(List<Hotel> hotels) {
        this.hotels = hotels;
    }
}
