package com.app.college.mobilecampus.todo.tododatabase;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import static android.provider.Contacts.SettingsColumns.KEY;

public class TodoDbHelper extends SQLiteOpenHelper {

    public static int DATABASE_VERSION = 1;
    public static String DATABASE_NAME = "todo.db";
    private static String DB_PATH = "";
    private final Context mContext;
    private SQLiteDatabase todoDB;
    private boolean mNeedUpdate = false;

    public TodoDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        if (android.os.Build.VERSION.SDK_INT >= 17)
            DB_PATH = context.getApplicationInfo().dataDir + "/databases/";
        else
            DB_PATH = "/data/data/" + context.getPackageName() + "/databases/";
        this.mContext = context;
        copyDataBase();
        this.getReadableDatabase();
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        //CREA TABLA MATERIAS
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS " + TodoMateriaEntry.TABLE_NAME + "("
                + TodoMateriaEntry.ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + TodoMateriaEntry.MATERIA + " TEXT NOT NULL" +
                ")");

        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS " + TodoTareaEntry.TABLE_NAME + "("
                + TodoTareaEntry.ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + TodoTareaEntry.NAME + " TEXT NOT NULL,"
                + TodoTareaEntry.DESCRIPTION + " TEXT NOT NULL,"
                + TodoTareaEntry.INIT_DATE + " TEXT NOT NULL,"
                + TodoTareaEntry.END_DATE + " TEXT NOT NULL,"
                + TodoTareaEntry.CALIFICACION + " REAL ,"
                + TodoTareaEntry.ID_MATERIA + " INTEGER , "
                + TodoTareaEntry.COMPLETA + " INTEGER ,"
                + " FOREIGN KEY(" + TodoTareaEntry.ID_MATERIA + ") REFERENCES " + TodoMateriaEntry.TABLE_NAME + "(" + TodoMateriaEntry.ID + ")" +
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        if (i1> i)
            mNeedUpdate = true;
    }

    private boolean checkDataBase() {
        File dbFile = new File(DB_PATH + DATABASE_NAME);
        return dbFile.exists();
    }

    private void copyDataBase() {
        if (!checkDataBase()) {
            this.getReadableDatabase();
            this.close();
            try {
                copyDBFile();
            } catch (IOException mIOException) {
                System.out.println("Error Copying Database");
            }
        }
    }
    private void copyDBFile() throws IOException {
        InputStream mInput = mContext.getAssets().open(DATABASE_NAME);
        //InputStream mInput = mContext.getResources().openRawResource(R.raw.info);
        OutputStream mOutput = new FileOutputStream(DB_PATH + DATABASE_NAME);
        byte[] mBuffer = new byte[1024];
        int mLength;
        while ((mLength = mInput.read(mBuffer)) > 0)
            mOutput.write(mBuffer, 0, mLength);
        mOutput.flush();
        mOutput.close();
        mInput.close();
    }
    public void updateDataBase() throws IOException {
        if (mNeedUpdate) {
            File dbFile = new File(DB_PATH + DATABASE_NAME);
            if (dbFile.exists())
                dbFile.delete();

            copyDataBase();

            mNeedUpdate = false;
        }
    }
    @Override
    public synchronized void close() {
        if (todoDB != null)
            todoDB.close();
        super.close();
    }
    public boolean openDataBase() throws SQLException {
        todoDB = SQLiteDatabase.openDatabase(DB_PATH + DATABASE_NAME, null, SQLiteDatabase.CREATE_IF_NECESSARY);
        return todoDB != null;
    }

}
