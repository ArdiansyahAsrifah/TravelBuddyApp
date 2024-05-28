package com.example.travelbuddy.Fragment;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.travelbuddy.R;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

public class BookingFragment extends Fragment {

    private EditText bookingDate, bookingTime, bookingName;
    private RadioGroup paymentMethod;
    private Button bookingButton;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_booking, container, false);

        bookingDate = root.findViewById(R.id.booking_date);
        bookingTime = root.findViewById(R.id.booking_time);
        bookingName = root.findViewById(R.id.booking_name);
        paymentMethod = root.findViewById(R.id.payment_method);
        bookingButton = root.findViewById(R.id.booking_button);

        bookingDate.setOnClickListener(v -> showDatePicker());
        bookingTime.setOnClickListener(v -> showTimePicker());

        bookingButton.setOnClickListener(v -> handleBooking());

        return root;
    }

    private void showDatePicker() {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),
                (view, year, month, dayOfMonth) -> bookingDate.setText(dayOfMonth + "/" + (month + 1) + "/" + year),
                calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    private void showTimePicker() {
        Calendar calendar = Calendar.getInstance();
        TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(),
                (view, hourOfDay, minute) -> bookingTime.setText(hourOfDay + ":" + minute),
                calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true);
        timePickerDialog.show();
    }

    private void handleBooking() {
        String date = bookingDate.getText().toString();
        String time = bookingTime.getText().toString();
        String name = bookingName.getText().toString();
        int selectedPaymentId = paymentMethod.getCheckedRadioButtonId();
        if (selectedPaymentId == -1) {
            Toast.makeText(getContext(), "Please select a payment method", Toast.LENGTH_SHORT).show();
            return;
        }
        String payment = ((RadioButton) requireView().findViewById(selectedPaymentId)).getText().toString();

        if (date.isEmpty() || time.isEmpty() || name.isEmpty() || selectedPaymentId == -1) {
            Toast.makeText(getContext(), "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }


        SharedPreferences sharedPreferences = getContext().getSharedPreferences("booking_data", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        Set<String> bookings = sharedPreferences.getStringSet("bookings", new HashSet<>());
        bookings.add("Date: " + date + ", Time: " + time + ", Name: " + name + ", Payment: " + payment);

        editor.putStringSet("bookings", bookings);
        editor.apply();


        TicketFragment ticketFragment = new TicketFragment();
        requireActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_container, ticketFragment)
                .addToBackStack(null)
                .commit();
    }
}
