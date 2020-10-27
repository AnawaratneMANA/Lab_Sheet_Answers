package com.example.final_paper_b_2019;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.final_paper_b_2019.Database.DBHandler;

import java.util.ArrayList;

public class GameOverview extends AppCompatActivity {

    private EditText comment;
    private SeekBar bar;
    private TextView current_rate;
    private TextView header;
    private ListView layout;
    private Button submit;

    private int currentProgress;
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_overview);

        Intent intent = getIntent();
        name  = intent.getStringExtra("name");


        comment = findViewById(R.id.comments);
        bar = findViewById(R.id.seekBar);
        current_rate = findViewById(R.id.current_rating);
        layout = findViewById(R.id.layout_2);
        header = findViewById(R.id.textView3);
        submit = findViewById(R.id.btn_submit);

        header.setText(name);
        bar.setMax(10);


        bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                currentProgress =i;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        DBHandler db = new DBHandler(GameOverview.this);
        ArrayList<String> comment_List = new ArrayList<>();
        comment_List = db.viewComments(name);
        double number = db.getCurrentRating(name);
        String current_rating_string = Double.toString(number);
        current_rate.setText(current_rating_string);

        ListAdapter adapter = new ArrayAdapter<>(GameOverview.this, android.R.layout.simple_list_item_1, comment_List);
        layout.setAdapter(adapter);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHandler db = new DBHandler(GameOverview.this);
                boolean status = db.insertComments(name,currentProgress, comment.getText().toString());
            }
        });


    }
}