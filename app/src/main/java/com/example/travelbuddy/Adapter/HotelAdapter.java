package com.example.travelbuddy.Adapter;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.travelbuddy.R;
import com.example.travelbuddy.Models.Hotel;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class HotelAdapter extends RecyclerView.Adapter<HotelAdapter.HotelViewHolder> {

    private final List<Hotel> hotels;
    private List<Hotel> filteredHotels;

    public HotelAdapter(List<Hotel> hotels) {
        this.hotels = hotels;
        this.filteredHotels = new ArrayList<>(hotels);
    }

    @NonNull
    @Override
    public HotelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_item, parent, false);
        return new HotelViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HotelViewHolder holder, int position) {
        Hotel hotel = filteredHotels.get(position);
        holder.hotelName.setText(hotel.getHotelName());
        holder.hotelCity.setText(hotel.getCity());
        holder.hotelClass.setText(String.valueOf(hotel.getHotelClass()));
    }

    @Override
    public int getItemCount() {
        return filteredHotels.size();
    }

    public void getFilter(String query) {
        filteredHotels.clear();
        if (TextUtils.isEmpty(query)) {
            filteredHotels.addAll(hotels);
        } else {
            String lowerCaseQuery = query.toLowerCase(Locale.getDefault());
            for (Hotel hotel : hotels) {
                if (hotel.getHotelName().toLowerCase(Locale.getDefault()).contains(lowerCaseQuery)) {
                    filteredHotels.add(hotel);
                }
            }
        }
        notifyDataSetChanged();
    }

    static class HotelViewHolder extends RecyclerView.ViewHolder {
        TextView hotelName;
        TextView hotelCity;
        TextView hotelClass;


        HotelViewHolder(@NonNull View itemView) {
            super(itemView);
            hotelName = itemView.findViewById(R.id.hotelNameTextView);
            hotelCity = itemView.findViewById(R.id.hotelCityTextView);
            hotelClass = itemView.findViewById(R.id.hotelClassTextView);
        }
    }
}
