package com.app.college.mobilecampus.ui.todo;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.college.mobilecampus.R;
import com.app.college.mobilecampus.todo.Materia;
import com.app.college.mobilecampus.todo.todoUtilSQL;
import com.app.college.mobilecampus.todo.tododatabase.TodoDbHelper;
import com.app.college.mobilecampus.todo.tododatabase.TodoMateriaEntry;

import java.io.IOException;
import java.util.ArrayList;

public class ToDoMateriasFragment extends Fragment {
    private ArrayList<Materia> list_materia;
    private final String DB_PATH = "/data/data/com.app.college.mobilecampus/databases/todo.db";
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView recyclerMaterias;
    private TodoDbHelper todoDbHelper;
    private SQLiteDatabase db;
    private static MateriaAdapter materiaAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.todo_materias_fragment,container,false);
        recyclerMaterias = v.findViewById(R.id.Recycler_Materia_View);
        layoutManager= new LinearLayoutManager(getActivity());
        recyclerMaterias.setLayoutManager(layoutManager);
        getListaMaterias();
        initAdapter();
        return v;
    }

    public void getListaMaterias(){
        list_materia = new ArrayList<>();
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
        Cursor c = todoUtilSQL.getAllMateria();

        if (c != null && c.getColumnCount() != 0) {
            try {
                c.moveToFirst();
                while (c.isAfterLast() == false) {
                    Materia materia = new Materia();
                    materia.setId(c.getInt(c.getColumnIndex(TodoMateriaEntry.ID)));
                    materia.setNombre(c.getString(c.getColumnIndex(TodoMateriaEntry.MATERIA)));
                    materia.setCorreo_profesor(c.getString(c.getColumnIndex(TodoMateriaEntry.CORREO_PROFESOR)));
                    list_materia.add(materia);
                    c.moveToNext();
                }
                db.close();
                todoDbHelper.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public void initAdapter(){
        materiaAdapter = new MateriaAdapter(list_materia);
        recyclerMaterias.setAdapter(materiaAdapter);
    }
}
