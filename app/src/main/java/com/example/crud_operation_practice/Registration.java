package com.example.crud_operation_practice;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Registration extends AppCompatActivity {

    //Declare all the elements - EditTexts
    private EditText user_name;
    private EditText Password;

    //Declare all the elements - RadioGroupd and RadioButtons
    private RadioGroup radioGroup;
    private RadioButton radio_Teacher;
    private RadioButton radio_Student;

    //Variables
    private int selected;
    private String name;
    private String password;

    //Buttons
    private Button btn_register;

    //Database level instance
    private DatabaseHelper databaseHelper;
    private SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        //Database Access creation - Optional, Already created in the DatabaseHelper class
        databaseHelper = new DatabaseHelper(this);
        db = databaseHelper.getWritableDatabase();

        //Register elements
        user_name = findViewById(R.id.username);
        Password = findViewById(R.id.password);
        radioGroup = findViewById(R.id.btnGroup);
        radio_Student = findViewById(R.id.btnStudent);
        radio_Teacher = findViewById(R.id.btnTeacher);
        btn_register = findViewById(R.id.btnRegistration);

        //Method.
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Writing the user information to the database
                name = user_name.getText().toString();
                password = Password.getText().toString();

                //Getting the selected button
                if(radio_Teacher.isChecked()){
                    selected = 0;
                } else if(radio_Student.isChecked()){
                    selected = 1;
                }

                boolean status = databaseHelper.addUser(name, password, selected);
                if(status == true){
                    Toast.makeText(getApplication(), "Data Inserted correctly", Toast.LENGTH_SHORT).show();
                } else if (status == false){
                    Toast.makeText(getApplication(), "Error while inserting data", Toast.LENGTH_SHORT).show();
                }
            }
        });




    }
}