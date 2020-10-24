package com.example.final_paper_a_2019.Activites;

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
import android.widget.Toast;

import com.example.final_paper_a_2019.Classes.DBHandler;
import com.example.final_paper_a_2019.R;

import java.util.ArrayList;

public class MovieOverview extends AppCompatActivity {

    private TextView header_movies;
    private SeekBar rating_bar;
    private TextView progress;
    private Button btn_submit;
    private EditText comment;
    private DBHandler db;
    private ListView layout_3;
    private TextView rating_average;

    //Seek bar variables
    int currentMax = 5;
    int currentSteps = 1;
    int currentProgress = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_overview);

        Intent intent = getIntent();
        final String movie_name = intent.getStringExtra("movie_name");

        //Set the movie
        header_movies = findViewById(R.id.header_movies);
        progress = findViewById(R.id.progress_label);
        header_movies.setText(movie_name);
        comment = findViewById(R.id.comment);
        rating_bar = findViewById(R.id.rating_bar);
        rating_bar.setMax(currentMax);
        btn_submit = findViewById(R.id.btn_submit);
        layout_3 = findViewById(R.id.layout_3);
        rating_average = findViewById(R.id.rating_average);

        db = new DBHandler(this);

        rating_bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                currentProgress = i;
                progress.setText("Ruler ( " + currentProgress + " ) :");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(currentProgress == 0) {
                    Toast.makeText(MovieOverview.this, "Rate the movie first!", Toast.LENGTH_SHORT).show();
                    return;
                }
                String comments = comment.getText().toString();
                boolean outcome = db.insertComments(movie_name, currentProgress, comments);
                populateUI(movie_name);

                if(outcome == true){
                    Toast.makeText(MovieOverview.this, "Comment Added!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MovieOverview.this, "Error in adding the comment!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        populateUI(movie_name);
    }

    //Populating method
    public void populateUI(String movie_name){
        ArrayList<String> comments = new ArrayList<>();
        double average = db.viewComments(movie_name);
        String double_average = Double.toString(average);
        comments = db.getComments();

        //Set Average to the TextView
        rating_average.setText(double_average);

        //Populate the List view
        ListAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,comments);
        layout_3.setAdapter(adapter);
    }
}