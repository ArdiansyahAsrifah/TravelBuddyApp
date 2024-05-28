package com.example.travelbuddy.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.example.travelbuddy.Fragment.HomeFragment;
import com.example.travelbuddy.Fragment.HotelFragment;
import com.example.travelbuddy.Fragment.ProfileFragment;
import com.example.travelbuddy.R;
import com.example.travelbuddy.Fragment.TicketFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frame_container, new HomeFragment())
                    .commit();
        }

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);


        bottomNav.setOnItemSelectedListener(item -> {
            Fragment selectedFragment = null;

            if (item.getItemId() == R.id.homemenu) {
                selectedFragment = new HomeFragment();
            } else if (item.getItemId() == R.id.hoteldetailmenu) {
                selectedFragment = new HotelFragment();
            } else if (item.getItemId() == R.id.ticketmenu) {
                selectedFragment = new TicketFragment();
            } else if (item.getItemId() == R.id.profilmenu) {
                selectedFragment = new ProfileFragment();
            }

            if (selectedFragment != null) {
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frame_container, selectedFragment)
                        .commit();

                return true;
            } else {
                return false;
            }
        });
    }
}
