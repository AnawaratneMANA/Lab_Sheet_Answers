package com.example.sampleemptyproject;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class EditProfile extends AppCompatActivity {

    private EditText Name;
    private EditText dateOfbirth;
    private EditText Password;
    private RadioButton Male;
    private RadioButton Female;
    private Button search;
    private Button update;
    private Button delete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);

        Name = findViewById(R.id.name);
        dateOfbirth = findViewById(R.id.dateofbirth);
        Male = findViewById(R.id.radioButtonMale);
        Female = findViewById(R.id.radioButtonFemale);
        Password = findViewById(R.id.password);
        search = findViewById(R.id.btn_search);
        update = findViewById(R.id.btn_search);
        delete = findViewById(R.id.btn_search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = Name.getText().toString();
                DBHelper db = new DBHelper(EditProfile.this);
                Cursor obj = db.readAllInfor();
                while(obj.moveToNext()){
                    if(obj.getString(obj.getColumnIndex(UserProfile.Users.COLUMN_NAME_1)).contentEquals(name)){
                        dateOfbirth.setText(obj.getString(obj.getColumnIndex(UserProfile.Users.COLUMN_NAME_2)));
                        String gender = (obj.getString(obj.getColumnIndex(UserProfile.Users.COLUMN_NAME_3)));
                        if(gender.contentEquals("Male")){
                            Male.setChecked(true);
                            Female.setChecked(false);
                        } else if (gender.contentEquals("Female")){
                            Male.setChecked(false);
                            Female.setChecked(true);
                        }

                    }
                }
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper db = new DBHelper(EditProfile.this);
                String gender = "-";
                if(Male.isSelected()){
                    gender = "Male";
                } else if(Female.isChecked()){
                    gender = "Female";
                }
                boolean status = db.updateInfor(Name.getText().toString(), dateOfbirth.getText().toString(), Password.getText().toString(), gender);
                if (status == true){
                    Toast.makeText(EditProfile.this, "Insertion Successfull.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(EditProfile.this, "Insertion falied.", Toast.LENGTH_SHORT).show();
                }
                ;
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper db = new DBHelper(EditProfile.this);
                boolean status = db.deleteInfo(Name.getText().toString());
                if (status == true){
                    Toast.makeText(EditProfile.this, "Deletion Successfull.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(EditProfile.this, "Deletion falied.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}