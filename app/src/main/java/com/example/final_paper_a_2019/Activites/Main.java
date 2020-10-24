package com.example.final_paper_a_2019.Activites;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.final_paper_a_2019.Classes.DBHandler;
import com.example.final_paper_a_2019.R;

public class Main extends AppCompatActivity {

    //Variables - UI Elements
    private EditText user_name;
    private EditText pass_word;
    private Button btn_login;
    private Button btn_register;

    //Variable - Java Variables
    private String username;
    private String password;
    private DBHandler db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        user_name = findViewById(R.id.username);
        pass_word = findViewById(R.id.password);
        btn_login = findViewById(R.id.btn_login);
        btn_register = findViewById(R.id.btn_register);

        db = new DBHandler(this);

        //Create a Click Listener
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username = user_name.getText().toString();
                password = pass_word.getText().toString();

                //Call the method.
                long return_value = db.registerUser(username, password);
                System.out.println(return_value);
                if(return_value > 0){
                    Toast.makeText(Main.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Main.this, "Error in registering", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username = user_name.getText().toString();
                password = pass_word.getText().toString();

                //Call the method.
                int return_value = db.loginUser(username, password);
                if(return_value == 1){
                    //Adming - Intent to Admin page.
                    Toast.makeText(Main.this, "Adming Login", Toast.LENGTH_SHORT).show();
                    Intent st = new Intent(Main.this, AddMovie.class );
                    startActivity(st);
                } else if (return_value == 2){
                    //Valid user - Intent to User page (List View)
                    Toast.makeText(Main.this, "User Login", Toast.LENGTH_SHORT).show();
                    Intent st = new Intent(Main.this, MovieList.class );
                    startActivity(st);
                    startActivity(st);
                } else if (return_value == 0){
                    //Wrong password - Valid User (Print Toast)
                    Toast.makeText(Main.this, "Password Wrong!", Toast.LENGTH_SHORT).show();
                }
            }

        });
    }
}