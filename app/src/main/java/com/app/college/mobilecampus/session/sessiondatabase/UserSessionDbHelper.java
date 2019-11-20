package com.app.college.mobilecampus.session.sessiondatabase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class UserSessionDbHelper extends SQLiteOpenHelper {

    public static int DATABASE_VERSION =2;
    public static String DATABASE_NAME="user_session.db";

    public UserSessionDbHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS "+UserSessionEntry.TABLE_NAME +"("
                + UserSessionEntry._ID  + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + UserSessionEntry.USER + " TEXT NOT NULL,"
                + UserSessionEntry.NAME + " TEXT NOT NULL,"
                + UserSessionEntry.LASTNAME + " TEXT NOT NULL,"
                + UserSessionEntry.EMAIL + " TEXT NOT NULL,"
                + UserSessionEntry.ACTIVE + " TEXT NOT NULL,"
                + UserSessionEntry.CODIGO + " TEXT NOT NULL "
                + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS "+UserSessionEntry.TABLE_NAME +"("
                + UserSessionEntry._ID  + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + UserSessionEntry.USER + " TEXT NOT NULL,"
                + UserSessionEntry.NAME + " TEXT NOT NULL,"
                + UserSessionEntry.LASTNAME + " TEXT NOT NULL,"
                + UserSessionEntry.EMAIL + " TEXT NOT NULL,"
                + UserSessionEntry.ACTIVE + " TEXT NOT NULL,"
                + UserSessionEntry.CODIGO + " TEXT NOT NULL "
                + ")");

    }

}
