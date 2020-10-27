package com.example.final_paper_b_2019.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBHandler extends SQLiteOpenHelper {

    ArrayList<String> comments;
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Database.db";


    //Tables
    private static final String SQL_1 =
            "CREATE TABLE " + DatabaseManager.Users.TABLE_NAME_1 + " (" +
                    DatabaseManager.Users._ID + " INTEGER PRIMARY KEY," +
                    DatabaseManager.Users.COLUMN_NAME_1_1 + " TEXT," +
                    DatabaseManager.Users.COLUMN_NAME_1_2 + " TEXT)";

    private static final String SQL_1_DELETE =
            "DROP TABLE IF EXISTS " + DatabaseManager.Users.TABLE_NAME_1;


    private static final String SQL_2 =
            "CREATE TABLE " + DatabaseManager.Game.TABLE_NAME_2 + " (" +
                    DatabaseManager.Game._ID + " INTEGER PRIMARY KEY," +
                    DatabaseManager.Game.COLUMN_NAME_2_1 + " TEXT," +
                    DatabaseManager.Game.COLUMN_NAME_2_2 + " TEXT)";

    private static final String SQL_2_DELETE =
            "DROP TABLE IF EXISTS " + DatabaseManager.Game.TABLE_NAME_2;


    private static final String SQL_3 =
            "CREATE TABLE " + DatabaseManager.Comments.TABLE_NAME_3 + " (" +
                    DatabaseManager.Comments._ID + " INTEGER PRIMARY KEY," +
                    DatabaseManager.Comments.COLUMN_NAME_3_1 + " TEXT," +
                    DatabaseManager.Comments.COLUMN_NAME_3_2 + " INTEGER," +
                    DatabaseManager.Comments.COLUMN_NAME_3_3 + " TEXT)";

    private static final String SQL_3_DELETE =
            "DROP TABLE IF EXISTS " + DatabaseManager.Comments.TABLE_NAME_3;


    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_1);
        db.execSQL(SQL_2);
        db.execSQL(SQL_3);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_1_DELETE);
        db.execSQL(SQL_2_DELETE);
        db.execSQL(SQL_3_DELETE);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public boolean registerUser(String username, String password){
        // Gets the data repository in write mode
        SQLiteDatabase db = getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(DatabaseManager.Users.COLUMN_NAME_1_1, username);
        values.put(DatabaseManager.Users.COLUMN_NAME_1_2, password);
        //values.put(DatabaseManager.Users.COLUMN_NAME_1_3, "'normal'");

        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(DatabaseManager.Users.TABLE_NAME_1, null, values);
        if(newRowId > 0){
            return true;
        } else {
            return false;
        }
    }

    public int loginUser(String username, String password){
        SQLiteDatabase db = getReadableDatabase();

        if(username.contentEquals("admin")){
            return 5;
        }

        Cursor cursor = db.query(
                DatabaseManager.Users.TABLE_NAME_1,   // The table to query
                null,             // The array of columns to return (pass null to get all)
                null,              // The columns for the WHERE clause
                null,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null               // The sort order
        );
        int flag = 0;
        while(cursor.moveToNext()){
            if(username.contentEquals(cursor.getString(cursor.getColumnIndex(DatabaseManager.Users.COLUMN_NAME_1_1)))){
                if(password.contentEquals(cursor.getString(cursor.getColumnIndex(DatabaseManager.Users.COLUMN_NAME_1_2)))){
                    flag = 1;
                    break;
                } else {
                    flag = 2;
                    break;
                }
            }
        }



        return flag;
    }

    public boolean addGame(String name, String year){
        // Gets the data repository in write mode
        SQLiteDatabase db = getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(DatabaseManager.Game.COLUMN_NAME_2_1, name);
        values.put(DatabaseManager.Game.COLUMN_NAME_2_2, year);

        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(DatabaseManager.Game.TABLE_NAME_2, null, values);
        if(newRowId > 0){
            return true;
        } else {
            return false;
        }
    }

    public ArrayList<String> viewGames(){
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<String> games = new ArrayList<>();
        Cursor cursor = db.query(
                DatabaseManager.Game.TABLE_NAME_2,   // The table to query
                null,             // The array of columns to return (pass null to get all)
                null,              // The columns for the WHERE clause
                null,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null               // The sort order
        );

        while(cursor.moveToNext()){
            games.add(cursor.getString(cursor.getColumnIndex(DatabaseManager.Game.COLUMN_NAME_2_1)));
        }

        return games;
    }

    public boolean insertComments(String game_name, int rating, String comment){
        // Gets the data repository in write mode
        SQLiteDatabase db = getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(DatabaseManager.Comments.COLUMN_NAME_3_1, game_name);
        values.put(DatabaseManager.Comments.COLUMN_NAME_3_2, rating);
        values.put(DatabaseManager.Comments.COLUMN_NAME_3_3, comment);

        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(DatabaseManager.Comments.TABLE_NAME_3, null, values);
        if(newRowId > 0){
            return true;
        } else {
            return false;
        }
    }

    public ArrayList<String> viewComments(String game_name){
        SQLiteDatabase db = getReadableDatabase();
        comments = new ArrayList<>();

        // Filter results WHERE "title" = 'My Title'
        String selection = DatabaseManager.Comments.COLUMN_NAME_3_1 + " = ?";
        String[] selectionArgs = { game_name };

        Cursor cursor = db.query(
                DatabaseManager.Comments.TABLE_NAME_3,   // The table to query
                null,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null               // The sort order
        );

        while(cursor.moveToNext()){
            comments.add(cursor.getString(cursor.getColumnIndex(DatabaseManager.Comments.COLUMN_NAME_3_1)));
        }

        return comments;
    }
    public double getCurrentRating(String game_name){

        SQLiteDatabase db = getReadableDatabase();
        comments = new ArrayList<>();

        // Filter results WHERE "title" = 'My Title'
        String selection = DatabaseManager.Comments.COLUMN_NAME_3_1 + " = ?";
        String[] selectionArgs = { game_name };

        Cursor cursor = db.query(
                DatabaseManager.Comments.TABLE_NAME_3,   // The table to query
                null,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null               // The sort order
        );
        double count = 0;
        double count_loop = 0;
        while(cursor.moveToNext()){
            count = count + Double.parseDouble(cursor.getString(cursor.getColumnIndex(DatabaseManager.Comments.COLUMN_NAME_3_2)));
            count_loop++;
        }

        return count/count_loop;
    }
}
