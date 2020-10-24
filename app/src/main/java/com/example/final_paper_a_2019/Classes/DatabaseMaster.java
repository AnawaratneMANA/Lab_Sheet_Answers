package com.example.final_paper_a_2019.Classes;

public final class DatabaseMaster {

    DatabaseMaster(){

    }

    //Static variable methods - User table
    public static final class Users{
        public static String user_table = "users";
        public static String username = "username";
        public static String password = "password";
        public static String user_type = "userType";
    }
    //Static variable methods - Movie table
    public static final class Movie{
        public static String movie_table = "movie";
        public static String movie_name = "movie_name";
        public static String movie_year = "year";
    }

    //Static variable method - Comments table
    public static final class Comments{
        public static String comment_table = "comment";
        public static String comment_movie_name = "comment_movie_name";
        public static String comment_rating = "rating";
        public static String comment_comments = "comment_movie_comments";
    }

}
