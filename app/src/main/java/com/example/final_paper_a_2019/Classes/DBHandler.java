package com.example.final_paper_a_2019.Classes;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import androidx.annotation.Nullable;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import static com.example.final_paper_a_2019.Classes.DatabaseMaster.Comments.comment_comments;
import static com.example.final_paper_a_2019.Classes.DatabaseMaster.Comments.comment_movie_name;
import static com.example.final_paper_a_2019.Classes.DatabaseMaster.Comments.comment_rating;
import static com.example.final_paper_a_2019.Classes.DatabaseMaster.Comments.comment_table;
import static com.example.final_paper_a_2019.Classes.DatabaseMaster.Movie.movie_name;
import static com.example.final_paper_a_2019.Classes.DatabaseMaster.Movie.movie_table;
import static com.example.final_paper_a_2019.Classes.DatabaseMaster.Movie.movie_year;
import static com.example.final_paper_a_2019.Classes.DatabaseMaster.Users.password;
import static com.example.final_paper_a_2019.Classes.DatabaseMaster.Users.user_table;
import static com.example.final_paper_a_2019.Classes.DatabaseMaster.Users.user_type;
import static com.example.final_paper_a_2019.Classes.DatabaseMaster.Users.username;

public class DBHandler extends SQLiteOpenHelper {

    //Creating List
    ArrayList<String> movies =  new ArrayList<String>();;
    ArrayList<String> years = new ArrayList<String>();;
    ArrayList<String> comments = new ArrayList<String>();;

    //Getters
    public ArrayList getMovies() {
        return movies;
    }

    public ArrayList getYears() {
        return years;
    }

    public ArrayList getComments() {
        return comments;
    }

    //Table creation Queries
    private static String SQL_TABLE_1 = "CREATE TABLE " + user_table
            +" ( userId INTEGER PRIMARY KEY AUTOINCREMENT, "  //Auto Increment column
            + username + " TEXT , "
            + password + " TEXT , "
            + user_type + " TEXT ) ";

    private static String SQL_TABLE_2 = "CREATE TABLE " + movie_table
            +" ( movieId INTEGER PRIMARY KEY AUTOINCREMENT, "  //Auto Increment column
            + movie_name + " TEXT , "
            + movie_year + " INTEGER ) ";

    private static String SQL_TABLE_3 = "CREATE TABLE " + comment_table
            +" ( commentId INTEGER PRIMARY KEY AUTOINCREMENT, "  //Auto Increment column
            + comment_movie_name + " TEXT , "
            + comment_rating + " INTEGER , "
            + comment_comments + " TEXT ) ";

    private static String DROP_SQL_TABLE_1 = "DROP TABLE " + user_table;
    private static String DROP_SQL_TABLE_2 = "DROP TABLE " + movie_table;
    private static String DROP_SQL_TABLE_3 = "DROP TABLE " + movie_table;

    //Variables
    private static String DB_NAME = "testing";
    private static int DB_VERSION =1;

    public DBHandler(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_TABLE_1);
        sqLiteDatabase.execSQL(SQL_TABLE_2);
        sqLiteDatabase.execSQL(SQL_TABLE_3);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(DROP_SQL_TABLE_1);
        sqLiteDatabase.execSQL(DROP_SQL_TABLE_2);
        sqLiteDatabase.execSQL(DROP_SQL_TABLE_3);
        onCreate(sqLiteDatabase);

    }

    //Other database method
    public long registerUser(String Username, String Password){

        //String formatting
        Username = "'" + Username+"'";
        Password = "'" + Password+"'";
        //DB
        SQLiteDatabase db = getWritableDatabase();

        //SQL - Inserting Method - I
        long status = -1;
        try{
            String SQL = "INSERT INTO " + user_table + " ( "
                    + username + ", " +
                    password + ", " +
                    user_type + " ) VALUES ( " + Username + ", " + Password + ", 'normal' )";
            SQLiteStatement statement = getWritableDatabase().compileStatement(SQL);
            status = statement.executeInsert();
            return status;
        } catch (SQLException e){
            e.printStackTrace();
            return status;
        }
    }

    //Login validation method
    public int loginUser(String userName, String Password){
        //Flags
        int flag = 0;
        //DB
        SQLiteDatabase db = getReadableDatabase();

        //Admin login validation
        if(userName.contentEquals("admin")){
            flag = 1;
            return flag;
        }

        //Normal user validation
        String SQL_1 = "SELECT * FROM " + user_table;

        //Executing query
        Cursor data = db.rawQuery(SQL_1,null);

        //Looping
        while (data.moveToNext()){
            String name = data.getString(data.getColumnIndex(username));
            if(name.contentEquals(userName)){
                if(Password.contentEquals(data.getString(data.getColumnIndex(password)))){
                    flag = 2;
                    return flag;
                }
                flag = 0;
            }
        }
        return flag;
    }

    //Create method addMovie()
    public boolean addMovies(String moviename, int movieyear){

        moviename = "'" + moviename+"'";

        //SQL - Inserting Method - I
        long return_value = -1;
        try{
            String SQL = "INSERT INTO " + movie_table + " ( "
                    + movie_name + ", " +
                    movie_year + " ) VALUES ( " + moviename + ", " + movieyear + " )";
            SQLiteStatement statement = getWritableDatabase().compileStatement(SQL);
            return_value = statement.executeInsert();
            if(return_value > 0){
                return true;
            }
        } catch (SQLException e){
            e.printStackTrace();
            return false;
        }
        return false;
    }

    public void viewMovies(){
        //SQL
        String SQL = "SELECT * FROM " + movie_table;
        Cursor data = getReadableDatabase().rawQuery(SQL, null);

        while(data.moveToNext()){
            //get data & add to the list
            movies.add(data.getString(data.getColumnIndex(movie_name)));
        }
    }

}
