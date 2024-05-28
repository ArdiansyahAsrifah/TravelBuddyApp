package com.example.travelbuddy.Adapter;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.travelbuddy.R;
import java.util.List;

public class TicketAdapter extends RecyclerView.Adapter<TicketAdapter.TicketViewHolder> {

    private final List<String> bookingList;

    public TicketAdapter(List<String> bookingList) {
        this.bookingList = bookingList;
    }

    @NonNull
    @Override
    public TicketViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ticket, parent, false);
        return new TicketViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TicketViewHolder holder, int position) {
        String booking = bookingList.get(position);
        holder.bookingInfo.setText(booking);


        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.itemView.getContext());
                builder.setTitle("Delete Booking");
                builder.setMessage("Are you sure you want to delete this booking?");
                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        bookingList.remove(position);

                        notifyItemRemoved(position);
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();
                    }
                });
                builder.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return bookingList.size();
    }

    static class TicketViewHolder extends RecyclerView.ViewHolder {

        TextView bookingInfo;
        View deleteButton;

        public TicketViewHolder(@NonNull View itemView) {
            super(itemView);
            bookingInfo = itemView.findViewById(R.id.booking_info);
            deleteButton = itemView.findViewById(R.id.btn_delete);
        }
    }
}
