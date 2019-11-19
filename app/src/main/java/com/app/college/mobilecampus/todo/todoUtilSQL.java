package com.app.college.mobilecampus.todo;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Spinner;
import android.widget.Switch;

import com.app.college.mobilecampus.todo.tododatabase.TodoDbHelper;
import com.app.college.mobilecampus.todo.tododatabase.TodoMateriaEntry;
import com.app.college.mobilecampus.todo.tododatabase.TodoTareaEntry;

import java.io.IOException;
import java.util.ArrayList;


public class todoUtilSQL {

    private SQLiteDatabase todoDB;

    public todoUtilSQL(SQLiteDatabase todoDB) {
        this.todoDB = todoDB;
    }



    public void insertTarea(String nombre, String descripcion, String fecha_inicio, String fecha_fin, double calificacion, int id_materia, int completada) {
        ContentValues values = new ContentValues();
        values.put(TodoTareaEntry.NAME, nombre);
        values.put(TodoTareaEntry.DESCRIPTION, descripcion);
        values.put(TodoTareaEntry.INIT_DATE, fecha_inicio);
        values.put(TodoTareaEntry.END_DATE, fecha_fin);
        values.put(TodoTareaEntry.CALIFICACION, calificacion);
        values.put(TodoTareaEntry.ID_MATERIA, id_materia);
        values.put(TodoTareaEntry.COMPLETA, completada);
        todoDB.insert(TodoTareaEntry.TABLE_NAME, null, values);
    }

    public void insertMateria(String nombre,String correo_profesor) {
        ContentValues values = new ContentValues();
        values.put(TodoMateriaEntry.MATERIA, nombre);
        values.put(TodoMateriaEntry.CORREO_PROFESOR,correo_profesor);
        todoDB.insert(TodoMateriaEntry.TABLE_NAME, null, values);
    }

    public Cursor getAllMateria() {
        return todoDB.query(TodoMateriaEntry.TABLE_NAME, null, null, null, null, null, null, null);
    }

    public Cursor getAllTarea(int sort) {

        switch(sort) {
            case 0:
                return todoDB.query(TodoTareaEntry.TABLE_NAME, null, null, null, null, null, TodoTareaEntry.END_DATE, null);
            case 1:
                return todoDB.query(TodoTareaEntry.TABLE_NAME, null, null, null, null, null, TodoTareaEntry.COMPLETA + " DESC", null);
            case 2:
                return todoDB.query(TodoTareaEntry.TABLE_NAME, null, null, null, null, null, TodoTareaEntry.COMPLETA+ " ASC", null);
            case 3:
                return todoDB.query(TodoTareaEntry.TABLE_NAME, null, null, null, null, null, TodoTareaEntry.CALIFICACION, null);
            case 4:
                return todoDB.query(TodoTareaEntry.TABLE_NAME, null, null, null, null, null, TodoTareaEntry.ID_MATERIA, null);
        }

        return todoDB.query(TodoTareaEntry.TABLE_NAME, null, null, null, null, null, null, null);
    }
    public Cursor getIdMateria(){
        String[] columns = {TodoMateriaEntry.ID};
        return todoDB.query(TodoMateriaEntry.TABLE_NAME, columns, TodoMateriaEntry.MATERIA, null, null, null, null, null);
    }

    public void updateTareaStatus(int id, int completada){
        ContentValues values = new ContentValues();
        values.put(TodoTareaEntry.COMPLETA,completada);
        todoDB.update(TodoTareaEntry.TABLE_NAME,values,TodoTareaEntry.ID + "="+id, null);
    }


}