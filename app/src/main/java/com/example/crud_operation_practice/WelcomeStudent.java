package com.example.crud_operation_practice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import com.example.crud_operation_practice.RecycleViewAdapter;

import java.util.ArrayList;

import static com.example.crud_operation_practice.Subject.subject_table.MESSAGE_NAME;
import static com.example.crud_operation_practice.Subject.subject_table.SUBJECT_NAME;

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
        //Database calling method should be here.
        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        //Getting all subject details.
        Cursor object = databaseHelper.getAllSubAndMessages();
        while(object.moveToNext()){
            headings.add(object.getString(object.getColumnIndex(SUBJECT_NAME)));
            //System.out.println(object.getString(object.getColumnIndex(SUBJECT_NAME)));
            descriptions.add(object.getString(object.getColumnIndex(MESSAGE_NAME)));
            //System.out.println(object.getString(object.getColumnIndex(MESSAGE_NAME)));
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