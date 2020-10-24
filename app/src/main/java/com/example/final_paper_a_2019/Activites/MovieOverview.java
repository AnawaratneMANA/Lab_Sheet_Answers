package com.example.final_paper_a_2019.Activites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.final_paper_a_2019.R;

public class MovieOverview extends AppCompatActivity {

    private TextView header_movies;
    private SeekBar rating_bar;
    private TextView progress;

    //Seek bar variables
    int currentMax = 5;
    int currentSteps = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_overview);

        Intent intent = getIntent();
        String movie_name = intent.getStringExtra("movie_name");

        //Set the movie
        header_movies = findViewById(R.id.header_movies);
        progress = findViewById(R.id.progress_label);
        header_movies.setText(movie_name);

        rating_bar = findViewById(R.id.rating_bar);
        rating_bar.setMax(currentMax);

        rating_bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                int currentProgress = i;
                progress.setText("Ruler ( " + currentProgress + " ) :");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}