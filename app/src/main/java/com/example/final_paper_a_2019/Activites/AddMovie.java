package com.example.final_paper_a_2019.Activites;
import com.example.final_paper_a_2019.Classes.DBHandler;
import com.example.final_paper_a_2019.R;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddMovie extends AppCompatActivity {

    private EditText movie_name;
    private EditText movie_year;
    private Button btn_add;

    private String moviename;
    private int movieyear;
    private DBHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_movie);

        movie_name = findViewById(R.id.moviename);
        movie_year = findViewById(R.id.movieyear);
        btn_add = findViewById(R.id.btn_add);

        db = new DBHandler(this);

        //Click Listener
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                moviename = movie_name.getText().toString();
                try{
                    movieyear = Integer.parseInt(movie_year.getText().toString());
                } catch( java.lang.NumberFormatException e){
                    e.printStackTrace();
                    Toast.makeText(AddMovie.this, "Year should be Integer!", Toast.LENGTH_SHORT).show();
                }

                //Call the Method
                boolean return_value = db.addMovies(moviename, movieyear);
                if(return_value == true){
                    Toast.makeText(AddMovie.this, "Movie inserted successfully", Toast.LENGTH_SHORT).show();
                } else{
                    Toast.makeText(AddMovie.this, "Error adding the movie!", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}