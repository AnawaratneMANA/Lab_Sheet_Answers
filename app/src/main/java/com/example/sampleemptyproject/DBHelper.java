package com.example.sampleemptyproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


    class DBHelper extends SQLiteOpenHelper {
        // If you change the database schema, you must increment the database version.
        public static final int DATABASE_VERSION = 1;
        public static final String DATABASE_NAME = "FeedReader.db";

        // Tables
        private final String SQL_CREATE_ENTRIES =
                "CREATE TABLE " + UserProfile.Users.TABLE_NAME + " (" +
                        UserProfile.Users._ID + " INTEGER PRIMARY KEY," +
                        UserProfile.Users.COLUMN_NAME_1 + " TEXT," +
                        UserProfile.Users.COLUMN_NAME_2 + " TEXT," +
                        UserProfile.Users.COLUMN_NAME_3 + " TEXT," +
                        UserProfile.Users.COLUMN_NAME_4 + " TEXT)";

        private final String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + UserProfile.Users.TABLE_NAME;

        public DBHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(SQL_CREATE_ENTRIES);
        }
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // This database is only a cache for online data, so its upgrade policy is
            // to simply to discard the data and start over
            db.execSQL(SQL_DELETE_ENTRIES);
            onCreate(db);
        }
        public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            onUpgrade(db, oldVersion, newVersion);
        }

        // Database methods
        public boolean addInfor(String name, String dateOfBirth, String gender, String password){
            // Gets the data repository in write mode
            SQLiteDatabase db = getWritableDatabase();

            // Create a new map of values, where column names are the keys
            ContentValues values = new ContentValues();
            values.put(UserProfile.Users.COLUMN_NAME_1, name);
            values.put(UserProfile.Users.COLUMN_NAME_2, dateOfBirth);
            values.put(UserProfile.Users.COLUMN_NAME_3, gender);
            values.put(UserProfile.Users.COLUMN_NAME_4, password);

            // Insert the new row, returning the primary key value of the new row
            long newRowId = db.insert(UserProfile.Users.TABLE_NAME, null, values);
            if(newRowId > 0){
                return true;
            }
            else {
                return false;
            }

        }

        // Update method
        public boolean updateInfor(String name, String dateOfBirth, String Password, String gender){
            SQLiteDatabase db = getWritableDatabase();

            // New value for one column
            String title = "MyNewTitle";
            ContentValues values = new ContentValues();
            values.put(UserProfile.Users.COLUMN_NAME_2, dateOfBirth);
            values.put(UserProfile.Users.COLUMN_NAME_3, Password);
            values.put(UserProfile.Users.COLUMN_NAME_4, gender);

            // Which row to update, based on the title
            String selection = UserProfile.Users.COLUMN_NAME_1 + " LIKE ?";
            String[] selectionArgs = { name };

            int count = db.update(
                    UserProfile.Users.TABLE_NAME,
                    values,
                    selection,
                    selectionArgs);
            //validation
            if(count > 0){
                return true;
            } else {
                return false;
            }

        }

        //read all data method
        public Cursor readAllInfor(){
            SQLiteDatabase db = getReadableDatabase();

            // Define a projection that specifies which columns from the database
            // you will actually use after this query.
            String[] projection = {
                    UserProfile.Users._ID,
                    UserProfile.Users.COLUMN_NAME_1,
                    UserProfile.Users.COLUMN_NAME_2,
                    UserProfile.Users.COLUMN_NAME_3,
                    UserProfile.Users.COLUMN_NAME_4,
            };

            // Filter results WHERE "title" = 'My Title'
            //String selection = UserProfile.Users.COLUMN_NAME_1 + " = ?";
            //String[] selectionArgs = { "My Title" };

            // How you want the results sorted in the resulting Cursor
            String sortOrder =
                    UserProfile.Users.COLUMN_NAME_1 + " DESC";

            Cursor cursor = db.query(
                    UserProfile.Users.TABLE_NAME,   // The table to query
                    projection,             // The array of columns to return (pass null to get all)
                    null,              // The columns for the WHERE clause
                    null,          // The values for the WHERE clause
                    null,                   // don't group the rows
                    null,                   // don't filter by row groups
                    sortOrder               // The sort order
            );

            return cursor;

        }

        //Method to delete users (specific)
        public boolean deleteInfo(String username){
            SQLiteDatabase db = getWritableDatabase();
            String selection = UserProfile.Users.COLUMN_NAME_1 + " LIKE ?";
            // Specify arguments in placeholder order.
            String[] selectionArgs = { username };
            // Issue SQL statement.
            int deletedRows = db.delete(UserProfile.Users.TABLE_NAME, selection, selectionArgs);

            //validation
            if(deletedRows > 0) {
                return true;
            } else {
                return false;
            }
        }
    }




