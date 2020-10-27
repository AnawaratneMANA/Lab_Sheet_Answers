package com.example.final_paper_b_2019;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.final_paper_b_2019.Database.DBHandler;

public class AddGame extends AppCompatActivity {

    private EditText name;
    private EditText year;
    private Button add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_game);

        name = findViewById(R.id.username);
        year = findViewById(R.id.password);
        add = findViewById(R.id.btn_add);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHandler db = new DBHandler(AddGame.this);

                boolean status = db.addGame(name.getText().toString(), year.getText().toString());
                if(status == true){
                    Toast.makeText(AddGame.this, "Success!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(AddGame.this, "Failed!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}