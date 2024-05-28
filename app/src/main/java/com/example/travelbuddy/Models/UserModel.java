package com.example.travelbuddy.Models;

import android.os.Parcel;
import android.os.Parcelable;

public class UserModel implements Parcelable {
    String name;
    String nim;
    int age;
    String phoneNumber;
    public UserModel(){
    }
    private UserModel(Parcel in) {
        this.name = in.readString();
        this.nim = in.readString();
        this.age = in.readInt();
        this.phoneNumber = in.readString();
    }
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(nim);
        dest.writeInt(age);
        dest.writeString(phoneNumber);
    }
    @Override
    public int describeContents() {
        return 0;
    }
    public static final Creator<UserModel> CREATOR = new Creator<UserModel>() {
        @Override
        public UserModel createFromParcel(Parcel source) {
            return new UserModel(source);
        }
        @Override
        public UserModel[] newArray(int size) {
            return new UserModel[size];
        }
    };
    public void setName(String name) {
        this.name = name;
    }
    public void setNim(String nim) {
        this.nim = nim;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }
    public String getNim() {
        return nim;
    }
    public int getAge() {
        return age;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }

}
