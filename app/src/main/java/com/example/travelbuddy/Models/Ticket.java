package com.example.travelbuddy.Models;

public class Ticket {
    private String bookingInfo;

    public Ticket(String bookingInfo) {
        this.bookingInfo = bookingInfo;
    }

    public String getBookingInfo() {
        return bookingInfo;
    }

    public void setBookingInfo(String bookingInfo) {
        this.bookingInfo = bookingInfo;
    }
}

