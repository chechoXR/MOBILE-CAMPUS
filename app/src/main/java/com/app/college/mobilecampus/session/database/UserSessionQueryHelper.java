package com.app.college.mobilecampus.session.database;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class UserSessionQueryHelper {

    public static final String[] PROJECTION = {UserSessionEntry.ID,UserSessionEntry.NAME,UserSessionEntry.LASTNAME,UserSessionEntry.USER, UserSessionEntry.EMAIL,UserSessionEntry.ACTIVE};
    public static final String SELECTION_USER = UserSessionEntry.USER +" = ?";
}
