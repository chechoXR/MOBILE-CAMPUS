package com.app.college.mobilecampus.ui.todo;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.college.mobilecampus.R;
import com.app.college.mobilecampus.todo.Tarea;
import com.app.college.mobilecampus.todo.todoUtilSQL;
import com.app.college.mobilecampus.todo.tododatabase.TodoDbHelper;
import com.app.college.mobilecampus.todo.tododatabase.TodoTareaEntry;

import java.io.IOException;
import java.util.ArrayList;

public class ToDoHomeFragment extends Fragment {
    private final String DB_PATH = "/data/data/com.app.college.mobilecampus/databases/todo.db";
    private RecyclerView listaTareas;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Tarea> tareas;
    private TodoDbHelper todoDbHelper;
    private SQLiteDatabase db;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.todo_home_fragment,container,false);
        listaTareas = v.findViewById(R.id.Recycler_Tarea_View);
        layoutManager= new LinearLayoutManager(getActivity());
        listaTareas.setLayoutManager(layoutManager);
        getListaTareas();
        initAdapter();
        return v;
    }
    private void initAdapter(){
        TareaAdapter myTareaAdapter = new TareaAdapter(tareas);
        listaTareas.setAdapter(myTareaAdapter);
    }
    private void getListaTareas() {
        tareas = new ArrayList<>();
        todoDbHelper = new TodoDbHelper(getContext());
        try {
            todoDbHelper.updateDataBase();
        } catch (IOException mIOException) {
            throw new Error("UnableToUpdateDatabase");
        }

        try {
            //db = todoDbHelper.getWritableDatabase();
            db= todoDbHelper.getReadableDatabase();
        } catch (SQLException mSQLException) {
            throw mSQLException;
        }
        // SQLiteDatabase db = SQLiteDatabase.openDatabase(DB_PATH,null,SQLiteDatabase.OPEN_READWRITE);
        todoUtilSQL todoUtilSQL = new todoUtilSQL(db);
        Cursor c = todoUtilSQL.getAllTarea();

        if (c != null && c.getColumnCount() != 0) {
            try {
                c.moveToFirst();
                while (c.isAfterLast() == false) {
                    Tarea tarea = new Tarea();
                    tarea.setId(c.getInt(c.getColumnIndex(TodoTareaEntry.ID)));
                    tarea.setNombre(c.getString(c.getColumnIndex(TodoTareaEntry.NAME)));
                    tarea.setDescripcion(c.getString(c.getColumnIndex(TodoTareaEntry.DESCRIPTION)));
                    tarea.setFecha_inicio(c.getString(c.getColumnIndex(TodoTareaEntry.INIT_DATE)));
                    tarea.setFecha_fin(c.getString(c.getColumnIndex(TodoTareaEntry.END_DATE)));
                    tarea.setCalificacion(c.getDouble(c.getColumnIndex(TodoTareaEntry.CALIFICACION)));
                    tarea.setCompletada(c.getInt(c.getColumnIndex(TodoTareaEntry.COMPLETA)));
                    tarea.setId_materia(c.getInt(c.getColumnIndex(TodoTareaEntry.ID_MATERIA)));
                    tareas.add(tarea);
                    c.moveToNext();
                }
                db.close();
                todoDbHelper.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }


}
