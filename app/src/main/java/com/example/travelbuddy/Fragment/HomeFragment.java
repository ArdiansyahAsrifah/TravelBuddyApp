package com.example.travelbuddy.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.travelbuddy.R;
import com.example.travelbuddy.Adapter.HotelAdapter;
import com.example.travelbuddy.Api.BookingApiService;
import com.example.travelbuddy.Retrofit.HotelResponse;
import com.example.travelbuddy.Retrofit.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    private RecyclerView recyclerView;
    private HotelAdapter hotelAdapter;
    private ProgressBar progressBar;

    private SearchView searchView;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = root.findViewById(R.id.recyclerView);
        progressBar = root.findViewById(R.id.progress_bar);
        searchView = root.findViewById(R.id.search_view1);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        showProgressBar();
        fetchHotels();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                hotelAdapter.getFilter(newText);
                return true;
            }
        });

        return root;


    }

    private void fetchHotels() {
        BookingApiService apiService = RetrofitClient.getClient().create(BookingApiService.class);
        Call<HotelResponse> call = apiService.getHotels(0);

        call.enqueue(new Callback<HotelResponse>() {
            @Override
            public void onResponse(Call<HotelResponse> call, Response<HotelResponse> response) {
                hideProgressBar();
                if (response.isSuccessful() && response.body() != null) {
                    hotelAdapter = new HotelAdapter(response.body().getHotels());
                    recyclerView.setAdapter(hotelAdapter);
                } else {
                    Toast.makeText(getContext(), "Failed to fetch data. Response code: " + response.code(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<HotelResponse> call, Throwable t) {
                hideProgressBar();
                Toast.makeText(getContext(), "Error: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });


    }

    private void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }
}

