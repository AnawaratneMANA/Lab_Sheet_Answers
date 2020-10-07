package com.example.crud_operation_practice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import com.example.crud_operation_practice.RecycleViewAdapter;

import java.util.ArrayList;

public class WelcomeStudent extends AppCompatActivity {

    private static final String TAG = "MainActivity";


    //Variables
    private ArrayList<String> headings = new ArrayList<>();
    private ArrayList<String> descriptions = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_student);
        Log.d(TAG, "onCreate: started");
        initHeadingDesBind();
    }

    //Method
    private void initHeadingDesBind(){
        //Database calling method should be here.\
        for(int i = 0; i<10; i++){
            headings.add("Sample" + i);
            descriptions.add("Sample" + i+2);
        }
        intiRecyclerView();
    }

    //Method to set the recyclerView
    private void intiRecyclerView(){
        Log.d(TAG, "initRecyclerView: inti recyclerview.");
        RecyclerView recyclerView = findViewById(R.id.recycle_view);
        RecycleViewAdapter adapter = new RecycleViewAdapter( headings, descriptions, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}