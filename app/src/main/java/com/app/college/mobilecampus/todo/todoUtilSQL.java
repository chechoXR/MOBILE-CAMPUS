package com.app.college.mobilecampus.todo;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.app.college.mobilecampus.todo.tododatabase.TodoMateriaEntry;
import com.app.college.mobilecampus.todo.tododatabase.TodoTareaEntry;


public class todoUtilSQL {

    private SQLiteDatabase todoDB;

    public todoUtilSQL(SQLiteDatabase todoDB) {
        this.todoDB = todoDB;
    }

    public void insertMateria(String nombre) {
        ContentValues values = new ContentValues();
        values.put(TodoMateriaEntry.MATERIA, nombre);
        todoDB.insert(TodoMateriaEntry.TABLE_NAME, null, values);
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

    public Cursor getAllMateria() {
        return todoDB.query(TodoMateriaEntry.TABLE_NAME, null, null, null, null, null, null, null);
    }

    public Cursor getAllTarea() {
        return todoDB.query(TodoTareaEntry.TABLE_NAME, null, null, null, null, null, null, null);
    }
    public Cursor getIdMateria(){
        String[] columns = {TodoMateriaEntry.ID};
        return todoDB.query(TodoMateriaEntry.TABLE_NAME, columns, TodoMateriaEntry.MATERIA, null, null, null, null, null);
    }
}