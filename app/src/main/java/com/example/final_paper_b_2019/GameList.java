package com.example.final_paper_b_2019;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.final_paper_b_2019.Database.DBHandler;

import java.util.ArrayList;
import java.util.List;

public class GameList extends AppCompatActivity {
    private ListView layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_list);

        layout = findViewById(R.id.layout_1);

        DBHandler db = new DBHandler(GameList.this);
        ArrayList<String> game_List = new ArrayList<>();
        game_List = db.viewGames();

        final ListAdapter adapter = new ArrayAdapter<>(GameList.this, android.R.layout.simple_list_item_1, game_List);
        layout.setAdapter(adapter);

        layout.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(GameList.this, GameOverview.class);
                intent.putExtra("name", adapterView.getItemAtPosition(i).toString());
                startActivity(intent);
            }
        });
    }
}