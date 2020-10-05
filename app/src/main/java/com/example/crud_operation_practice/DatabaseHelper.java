package com.example.crud_operation_practice;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.example.crud_operation_practice.Subject.subject_table.MESSAGE_NAME;
import static com.example.crud_operation_practice.Subject.subject_table.SUBJECT_MESSAGE_ID;
import static com.example.crud_operation_practice.Subject.subject_table.SUBJECT_MESSAGE_TABLE;
import static com.example.crud_operation_practice.Subject.subject_table.SUBJECT_NAME;
import static com.example.crud_operation_practice.User.user_table.USER_ID;
import static com.example.crud_operation_practice.User.user_table.USER_NAME;
import static com.example.crud_operation_practice.User.user_table.USER_PASSWORD;
import static com.example.crud_operation_practice.User.user_table.USER_TABLE;
import static com.example.crud_operation_practice.User.user_table.USER_TYPE;

public class DatabaseHelper extends SQLiteOpenHelper {

    //Constructor
    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME,null,DATABASE_VERSION);

    }
    //Database informations
    private static final String DATABASE_NAME = "CRUDbase_db";
    private static final int DATABASE_VERSION = 1;

    //Tables
    //Table - 1 -- USER DETAILS TABLE.
    private static final String USER_TABLE_QUERY =
            "CREATE TABLE " + USER_TABLE +
            " ( " + USER_ID +" INTEGER  PRIMARY KEY AUTOINCREMENT, " +
                    USER_NAME +" TEXT, " +
                    USER_PASSWORD +" TEXT, " + USER_TYPE +" INEGER );";
    private static final String DROP_USER_TABLE_QUERY = "DROP TABLE IF EXISTS " + USER_TABLE;

    //Table - 2 -- SUBJECT MESSAGE TABLE.
    private static final String SUBJECT_MESSAGE_TABLE_QUERY =  "CREATE TABLE " + SUBJECT_MESSAGE_TABLE +
            " ( " + SUBJECT_MESSAGE_ID +" INTEGER  PRIMARY KEY AUTOINCREMENT, " +
            SUBJECT_NAME +" TEXT, " +
            MESSAGE_NAME +" TEXT );";
    private static final String DROP_SUBJECT_MESSAGE_TABLE_QUERY = "DROP TABLE IF EXISTS " + SUBJECT_MESSAGE_TABLE;

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //Create table
        sqLiteDatabase.execSQL(USER_TABLE_QUERY);
        sqLiteDatabase.execSQL(SUBJECT_MESSAGE_TABLE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //Dropping tables on upgrade
        sqLiteDatabase.execSQL(DROP_USER_TABLE_QUERY);
        sqLiteDatabase.execSQL(DROP_SUBJECT_MESSAGE_TABLE_QUERY);
        onCreate(sqLiteDatabase);
    }

    //Database methoods
    public boolean addUser(String name, String password,int type){
        //Database Instance
        SQLiteDatabase db = getWritableDatabase();
        //String formatting
        name = " '"+name+"' ";
        password = " '"+password+"' ";
        String sql = "INSERT INTO " +USER_TABLE +" ("+ USER_NAME +", "+ USER_PASSWORD +", "+ USER_TYPE +" ) " +
                " VALUES ( " + name + ", " + password + ", " + type +" )";

        //Execute Query
        try{
            db.execSQL(sql);
            return true;
        } catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    //Create method to read all names in the database.
    public Cursor readAllUsers(){
        //Database Instance
        SQLiteDatabase db = getWritableDatabase();
        String sql = "SELECT * " +
                " FROM " + USER_TABLE;
        //Execute the query
        Cursor data = db.rawQuery(sql, null);
        //return
        return data;
    }

    //Create a method to insert subject messages to the database.
    public boolean insertSubject(String name, String message){
        //Database Instance
        SQLiteDatabase db = getWritableDatabase();

        //String formatting
        name = " '"+name+"' ";
        message = " '"+message+"' ";

        String sql = "INSERT INTO " + SUBJECT_MESSAGE_TABLE + " ( " + SUBJECT_NAME +", "+ MESSAGE_NAME + " ) VALUES ( " +
                name + ", " + message +" )";

        //Execute Query
        try{
            db.execSQL(sql);
            return true;
        } catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }
}
