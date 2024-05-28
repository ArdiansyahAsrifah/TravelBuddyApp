package com.example.travelbuddy.Models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Hotel implements Serializable {

    @SerializedName("name")
    private String hotelName;

    @SerializedName("city")
    private String city;

    @SerializedName("hotel_class")
    private int hotelClass;

    @SerializedName("address")
    private String address;

    @SerializedName("hotel_description")
    private String hotelDescription;

    @SerializedName("country")
    private String country;

    @SerializedName("district_id")
    private int districtId;

    @SerializedName("city_id")
    private int cityId;

    @SerializedName("url")
    private String url;

    @SerializedName("zip")
    private String zip;

    @SerializedName("currency")
    private String currency;

    @SerializedName("default_language")
    private String defaultLanguage;

    @SerializedName("exact_class")
    private int exactClass;

    @SerializedName("ranking")
    private int ranking;

    @SerializedName("hotel_type_id")
    private int hotelTypeId;

    @SerializedName("max_rooms_in_reservation")
    private int maxRoomsInReservation;

    @SerializedName("number_of_rooms")
    private int numberOfRooms;

    @SerializedName("license_number")
    private String licenseNumber;

    @SerializedName("book_domestic_without_cc_details")
    private boolean bookDomesticWithoutCcDetails;

    @SerializedName("is_work_friendly")
    private boolean isWorkFriendly;

    @SerializedName("class_is_estimated")
    private boolean classIsEstimated;

    @SerializedName("max_persons_in_reservation")
    private boolean maxPersonsInReservation;

    @SerializedName("creditcard_required")
    private boolean creditCardRequired;

    @SerializedName("preferred")
    private boolean preferred;

    @SerializedName("is_closed")
    private boolean isClosed;

    @SerializedName("spoken_languages")
    private String spokenLanguages;

    @SerializedName("latitude")
    private double latitude;

    @SerializedName("longitude")
    private double longitude;

    private String hotelImage;


    public String getHotelName() {
        return hotelName;
    }

    public String getCity() {
        return city;
    }

    public int getHotelClass() {
        return hotelClass;
    }

}
