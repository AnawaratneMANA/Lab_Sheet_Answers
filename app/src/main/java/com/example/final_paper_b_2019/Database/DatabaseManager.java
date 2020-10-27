package com.example.final_paper_b_2019.Database;

import android.provider.BaseColumns;

public final class DatabaseManager {
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private DatabaseManager() {}

    /* Inner class that defines the table contents */
    public static class Users implements BaseColumns {
        public static final String TABLE_NAME_1 = "users";
        public static final String COLUMN_NAME_1_1 = "Username";
        public static final String COLUMN_NAME_1_2 = "Password";
        public static final String COLUMN_NAME_1_3 = "userType";
    }

    public static class Game implements BaseColumns {
        public static final String TABLE_NAME_2 = "games";
        public static final String COLUMN_NAME_2_1 = "GameName";
        public static final String COLUMN_NAME_2_2 = "GameYear";
    }

    public static class Comments implements BaseColumns {
        public static final String TABLE_NAME_3 = "comments";
        public static final String COLUMN_NAME_3_1 = "GameName";
        public static final String COLUMN_NAME_3_2 = "GameRating";
        public static final String COLUMN_NAME_3_3 = "GameComments";
    }
}
