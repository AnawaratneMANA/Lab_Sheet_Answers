package com.example.crud_operation_practice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static com.example.crud_operation_practice.User.user_table.USER_NAME;
import static com.example.crud_operation_practice.User.user_table.USER_PASSWORD;
import static com.example.crud_operation_practice.User.user_table.USER_TYPE;

public class Login extends AppCompatActivity {

    private Button btn_register;
    private Button btn_login;

    //EditTexts
    private EditText user_name;
    private EditText Password;

    //Database Objects
    DatabaseHelper database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_register = findViewById(R.id.btnRegistration);
        btn_login = findViewById(R.id.btnLogin);

        user_name = findViewById(R.id.username);
        Password = findViewById(R.id.password);

        //Listener for the registration button
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Registration.class);
                startActivity(intent);
            }
        });

        //Listener for the Login button
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Variables
                int accept = 3;
                //Get Entered information
                String name = user_name.getText().toString();
                String password = Password.getText().toString();

                //Call the database data
                database = new DatabaseHelper(getApplication());
                Cursor object = database.readAllUsers();
                while(object.moveToNext()){
                    if(name.contentEquals(object.getString(object.getColumnIndex(USER_NAME)))){
                        if(password.contentEquals(object.getString(object.getColumnIndex(USER_PASSWORD)))){
                            String type = object.getString(object.getColumnIndex(USER_TYPE));
                            if( Integer.parseInt(type) == 0){
                                accept = 0;
                            } else if( Integer.parseInt(type) == 1){
                                accept = 1;
                            }
                        } else {
                            accept = 3;
                        }
                    }
                }

                //Intent to the next page
                if(accept == 1){
                    Intent intent = new Intent(getApplicationContext(), WelcomeStudent.class);
                    System.out.println("---------------------- Student" + accept);
                    startActivity(intent);
                } else if (accept == 0){
                    Intent intent = new Intent(getApplicationContext(), WelcomeTeacher.class);
                    System.out.println("---------------------- Teacher" + accept);
                    startActivity(intent);
                } else if(accept == 3){
                    Toast.makeText(Login.this, "Error Password or Username incorrect", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}