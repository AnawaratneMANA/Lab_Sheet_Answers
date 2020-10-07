package com.example.crud_operation_practice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class EditUpdateSubjects extends AppCompatActivity {

    //Elements
    private TextView Subject;
    private TextView Description;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_update_subjects);

        Intent intent = getIntent();
        String subject = intent.getStringExtra("subject");
        String description = intent.getStringExtra("description");

        //Setting the values in the TextViews
        Subject = findViewById(R.id.subject_header);
        Description = findViewById(R.id.message);

        Subject.setText(subject);
        Description.setText(description);


    }
}