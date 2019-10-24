package com.app.college.mobilecampus.todo.tododatabase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static android.provider.Contacts.SettingsColumns.KEY;

public class TodoDbHelper extends SQLiteOpenHelper {

    public static int DATABASE_VERSION = 1;
    public static String DATABASE_NAME = "todo.db";

    public TodoDbHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        //CREA TABLA MATERIAS
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS "+TodoMateriaEntry.TABLE_NAME + "("
                + TodoMateriaEntry.ID + "INTEGER PRIMARY KEY AUTOINCREMENT,"
                + TodoMateriaEntry.MATERIA + "TEXT NOT NULL"+
                ")");

        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS "+TodoTareaEntry.TABLE_NAME + "("
                + TodoTareaEntry.ID + "INTEGER PRIMARY KEY AUTOINCREMENT,"
                + TodoTareaEntry.NAME + "TEXT NOT NULL,"
                + TodoTareaEntry.DESCRIPTION + "TEXT NOT NULL,"
                + TodoTareaEntry.INIT_DATE + "TEXT NOT NULL,"
                + TodoTareaEntry.END_DATE + "TEXT NOT NULL,"
                + TodoTareaEntry.CALIFICACION + "REAL ,"
                + TodoTareaEntry.ID_MATERIA + "INTEGER , "
                + TodoTareaEntry.COMPLETA + "INTEGER ,"
                + "FOREIGN KEY("+TodoTareaEntry.ID_MATERIA+") REFERENCES "+TodoMateriaEntry.TABLE_NAME+"("+TodoMateriaEntry.ID+")"+
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

}
