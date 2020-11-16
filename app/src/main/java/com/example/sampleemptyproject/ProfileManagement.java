package com.example.sampleemptyproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class ProfileManagement extends AppCompatActivity {

    private Button update_profile;
    private EditText uname;
    private EditText birthdate;
    private EditText password;
    private RadioButton Male;
    private RadioButton Female;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        update_profile = findViewById(R.id.btn_register);
        uname = findViewById(R.id.uname);
        birthdate = findViewById(R.id.dateofbirth);
        password = findViewById(R.id.password);
        Male = findViewById(R.id.radioButtonMale);
        Female = findViewById(R.id.radioButtonFemale);
        update_profile = findViewById(R.id.btn_register);
        update_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = uname.getText().toString();
                String dateofBirth = birthdate.getText().toString();
                String pass = password.getText().toString();
                String gender = "-";
                if(Male.isSelected()){
                    gender = "Male";
                } else if(Female.isChecked()){
                    gender = "Female";
                }
                DBHelper db = new DBHelper(ProfileManagement.this);
                boolean status = db.addInfor(name, dateofBirth, pass, gender);
                if (status == true){
                    Toast.makeText(ProfileManagement.this, "Insertion Successfull.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ProfileManagement.this, "Insertion falied.", Toast.LENGTH_SHORT).show();
                }

                Intent intent = new Intent(ProfileManagement.this, ProfileManagement.class);
                intent.putExtra("name", name);
                startActivity(intent);
            }
        });
    }


}