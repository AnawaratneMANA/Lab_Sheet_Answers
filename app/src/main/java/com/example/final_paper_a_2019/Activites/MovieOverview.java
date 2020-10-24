package com.example.final_paper_a_2019.Activites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.final_paper_a_2019.R;

public class MovieOverview extends AppCompatActivity {

    private TextView header_movies;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_overview);

        Intent intent = getIntent();
        String movie_name = intent.getStringExtra("movie_name");

        //Set the movie
        header_movies = findViewById(R.id.header_movies);
        header_movies.setText(movie_name);
    }
}