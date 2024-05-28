package com.example.travelbuddy.Greetings;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.travelbuddy.R;

public class greeting2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_greeting2);

        Button startButton = findViewById(R.id.btn_next2);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(greeting2.this, ActivityGreeting3.class);
                startActivity(intent);
            }
        });
    }
}