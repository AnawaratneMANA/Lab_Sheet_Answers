package com.example.labsheet4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //Getting the buttons
    private Button frag_1_btn;
    private Button frag_2_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Registering the elements
        frag_1_btn = findViewById(R.id.fragment1);
        frag_2_btn = findViewById(R.id.fragment2);

        //Creating Listeners
        frag_2_btn.setOnClickListener(this);
        frag_1_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {


        //Validate the input
        if(view.getId() == R.id.fragment1){
            Fragment fragment = new fragment1();
            FragmentManager FM = getSupportFragmentManager();
            FragmentTransaction FT = FM.beginTransaction();
            FT.replace(R.id.fragment_container, fragment);
            FT.commit();
        } else if(view.getId() == R.id.fragment2){
            Fragment fragment = new fragment2();
            FragmentManager FM = getSupportFragmentManager();
            FragmentTransaction FT = FM.beginTransaction();
            FT.replace(R.id.fragment_container, fragment);
            FT.commit();
        }
    }
}