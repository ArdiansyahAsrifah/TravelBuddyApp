package com.example.travelbuddy.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.travelbuddy.Fragment.BookingFragment;
import com.example.travelbuddy.R;
import com.example.travelbuddy.Models.Hotel;

import java.util.List;

public class PopularAdapter extends RecyclerView.Adapter<PopularAdapter.ViewHolder> {

    private List<Hotel> hotels;
    private AppCompatActivity activity;

    public PopularAdapter(List<Hotel> hotels, AppCompatActivity activity) {
        this.hotels = hotels;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Hotel hotel = hotels.get(position);
        holder.hotelName.setText(hotel.getHotelName());

        holder.bookingButton.setOnClickListener(v -> {
            Fragment bookingFragment = new BookingFragment();
            activity.getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frame_container, bookingFragment)
                    .addToBackStack(null)
                    .commit();
        });
    }

    @Override
    public int getItemCount() {
        return hotels.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView hotelName;
        Button bookingButton;

        public ViewHolder(View itemView) {
            super(itemView);
            hotelName = itemView.findViewById(R.id.text_hotel_name);
            bookingButton = itemView.findViewById(R.id.btn_booking);
        }
    }
}
