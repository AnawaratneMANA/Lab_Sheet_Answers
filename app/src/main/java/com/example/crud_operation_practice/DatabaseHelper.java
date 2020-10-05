package com.example.crud_operation_practice;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.example.crud_operation_practice.User.user_table.USER_ID;
import static com.example.crud_operation_practice.User.user_table.USER_NAME;
import static com.example.crud_operation_practice.User.user_table.USER_TABLE;

public class DatabaseHelper extends SQLiteOpenHelper {

    //Constructor
    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME,null,DATABASE_VERSION);

    }
    //Database informations
    private static final String DATABASE_NAME = "CRUDbase_db";
    private static final int DATABASE_VERSION = 1;

    //Tables
    private static final String USER_TABLE_QUERY=
            "CREATE TABLE " + USER_TABLE +
            "( " + USER_ID +"INTEGER  PRIMARY KEY AUTOINCREMENT," +
                    USER_NAME +" TEXT );";
    private static final String DROP_USER_TABLE_QUERY = "DROP TABLE IF EXIST " + USER_TABLE;


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //Create table
        sqLiteDatabase.execSQL(USER_TABLE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //Droping tables on upgrade
        sqLiteDatabase.execSQL(DROP_USER_TABLE_QUERY);
    }

    //Database methoods
}
