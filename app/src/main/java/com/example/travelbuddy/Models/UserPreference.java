package com.example.travelbuddy.Models;

import android.content.Context;
import android.content.SharedPreferences;

public class UserPreference {
    private static final String PREFS_NAME = "user_pref";
    private static final String NAME = "name";
    private static final String NIM = "nim";
    private static final String AGE = "age";
    private static final String PHONE_NUMBER = "phone";
    private final SharedPreferences preferences;
    public UserPreference(Context context) {
        preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }
    public void setUser(UserModel value) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(NAME, value.name);
        editor.putString(NIM, value.nim);
        editor.putInt(AGE, value.age);
        editor.putString(PHONE_NUMBER, value.phoneNumber);
        editor.apply();
    }
    public UserModel getUser() {
        UserModel model = new UserModel();
        model.setName(preferences.getString(NAME, ""));
        model.setNim(preferences.getString(NIM, ""));
        model.setAge(preferences.getInt(AGE, 0));
        model.setPhoneNumber(preferences.getString(PHONE_NUMBER, ""));
        return model;
    }
}