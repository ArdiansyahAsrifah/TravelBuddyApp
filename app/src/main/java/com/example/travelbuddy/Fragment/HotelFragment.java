package com.example.travelbuddy.Fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.travelbuddy.R;
import com.example.travelbuddy.Adapter.PopularAdapter;
import com.example.travelbuddy.Api.BookingApiService;
import com.example.travelbuddy.Models.Hotel;
import com.example.travelbuddy.Retrofit.HotelResponse;
import com.example.travelbuddy.Retrofit.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class HotelFragment extends Fragment {

    private RecyclerView recyclerView;

    private ProgressBar progressBar;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_hotel, container, false);

        recyclerView = root.findViewById(R.id.recyclerView3);
        progressBar = root.findViewById(R.id.progress_bar);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        new FetchHotelsTask().execute();

        return root;
    }

    private class FetchHotelsTask extends AsyncTask<Void, Void, List<Hotel>> {

        @Override
        protected void onPreExecute() {
            showProgressBar();
        }

        @Override
        protected List<Hotel> doInBackground(Void... voids) {
            BookingApiService apiService = RetrofitClient.getClient().create(BookingApiService.class);
            Call<HotelResponse> call = apiService.getHotels(0);

            try {
                Response<HotelResponse> response = call.execute();
                if (response.isSuccessful() && response.body() != null) {
                    return response.body().getHotels();
                } else {
                    return null;
                }
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(List<Hotel> hotels) {

            if (isAdded()) {
                hideProgressBar();
                if (hotels != null) {
                    PopularAdapter adapter = new PopularAdapter(hotels, (AppCompatActivity) requireActivity());
                    recyclerView.setAdapter(adapter);
                } else {
                    Toast.makeText(getContext(), "Failed to fetch data.", Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    private void showProgressBar() {
        if (isAdded()) {
            progressBar.setVisibility(View.VISIBLE);
        }
    }

    private void hideProgressBar() {
        if (isAdded()) {
            progressBar.setVisibility(View.GONE);
        }
    }
}
