package com.example.final_paper_a_2019.Activites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.final_paper_a_2019.Classes.DBHandler;
import com.example.final_paper_a_2019.R;

import java.util.ArrayList;

public class MovieList extends AppCompatActivity {

    private ListView listView;
    private DBHandler db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);

        listView = (ListView) findViewById(R.id.layout_2);
        db = new DBHandler(this);

        //Populating the List View
        db.viewMovies();
        ArrayList<String> Movies = new ArrayList<>();
        Movies = db.getMovies();
        ListAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,Movies);
        listView.setAdapter(adapter);

        //Create a ClickListener to the List View
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String Moviename  = adapterView.getItemAtPosition(i).toString();
                Intent intent = new Intent(MovieList.this, MovieOverview.class);
                intent.putExtra("movie_name", Moviename);
                startActivity(intent);
            }
        });

    }
}