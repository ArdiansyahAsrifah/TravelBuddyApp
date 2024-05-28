package com.example.travelbuddy.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.travelbuddy.Adapter.TicketAdapter;
import com.example.travelbuddy.R;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TicketFragment extends Fragment {

    private RecyclerView recyclerViewTickets;
    private TicketAdapter ticketAdapter;
    private List<String> ticketList;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_ticket, container, false);

        recyclerViewTickets = root.findViewById(R.id.recyclerViewTickets);
        recyclerViewTickets.setLayoutManager(new LinearLayoutManager(getContext()));

        ticketList = new ArrayList<>();
        ticketAdapter = new TicketAdapter(ticketList);
        recyclerViewTickets.setAdapter(ticketAdapter);

        loadBookingData();

        return root;
    }

    private void loadBookingData() {
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("booking_data", Context.MODE_PRIVATE);
        Set<String> bookings = sharedPreferences.getStringSet("bookings", new HashSet<>());

        ticketList.clear();
        ticketList.addAll(bookings);
        ticketAdapter.notifyDataSetChanged();
    }
}
