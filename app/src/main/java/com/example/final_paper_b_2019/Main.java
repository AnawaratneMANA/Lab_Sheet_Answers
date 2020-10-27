package com.example.final_paper_b_2019;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.final_paper_b_2019.Database.DBHandler;

public class Main extends AppCompatActivity {

    private EditText name;
    private EditText pass;
    private Button login;
    private Button register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.username);
        pass = findViewById(R.id.password);
        login = findViewById(R.id.btn_login);
        register = findViewById(R.id.btn_register);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHandler db = new DBHandler(Main.this);
                boolean status = db.registerUser(name.getText().toString(), pass.getText().toString());
                if(status == true){
                    Toast.makeText(Main.this, "Success!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Main.this, "Failed!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHandler db = new DBHandler(Main.this);
                int status = db.loginUser(name.getText().toString(), pass.getText().toString());
                if(status == 5){
                    Intent intent = new Intent(Main.this, AddGame.class);
                    startActivity(intent);
                } else if(status == 1){
                    Intent intent = new Intent(Main.this, GameList.class);
                    startActivity(intent);
                } else if(status == 2){
                    Toast.makeText(Main.this, "Password Wrong!", Toast.LENGTH_SHORT).show();
                } else if(status == 0){
                    Toast.makeText(Main.this, "No User found!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}