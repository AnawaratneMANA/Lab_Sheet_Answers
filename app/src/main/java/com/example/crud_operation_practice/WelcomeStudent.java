package com.example.crud_operation_practice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

public class WelcomeStudent extends AppCompatActivity {

    private ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_student);
        list = findViewById(R.id.ListView);

        //Get information to display.
        //How to display subject name and the description both in the list view!?


    }
}