package com.example.se_project;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper
{
    public static final String DATABASE_NAME="registration.db";
    public static final String TABLE_NAME="registraion";
    public static final String COL_1="id";
    public static final String COL_2="username";
    public static final String COL_3="password";
    public static final String COL_4="last_name";
    public static final String COL_5="first_name";
    public static final String COL_6="email";
    public static final String COL_7="phone_num";
    public static final String COL_8="street_address";
    public static final String COL_9="city";
    public static final String COL_10="state";
    public static final String COL_11="zipcode";
    public static final String COL_12="type_of_user";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE "+TABLE_NAME+" (id INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT, password TEXT, last_name TEXT, first_name TEXT, email TEXT, phone_num INTEGER, street_address TEXT, city TEXT, state TEXT, zipcode INTEGER, type_of_user TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);


    }
}
