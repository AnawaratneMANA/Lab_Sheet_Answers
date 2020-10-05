package com.example.crud_operation_practice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class WelcomeTeacher extends AppCompatActivity {
    //Buttons
    private Button Send;

    //Edit texts
    private EditText Subject;
    private EditText Message;

    //Database level instances
    private DatabaseHelper databaseHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_teacher);

        //Register elements
        Send = findViewById(R.id.btnSend);
        Subject = findViewById(R.id.subject);
        Message = findViewById(R.id.message);

        //Click Listener
        Send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //DB
                databaseHelper = new DatabaseHelper(getApplication());
                //Values
                String subject = Subject.getText().toString();
                String message = Message.getText().toString();
                //Calling the method
                boolean status = databaseHelper.insertSubject(subject, message);

                //Confirmation
                if(status == true){
                    Toast.makeText(WelcomeTeacher.this, "Data added", Toast.LENGTH_SHORT).show();
                } else if (status == false){
                    Toast.makeText(WelcomeTeacher.this, "Fault in data insertion,", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}