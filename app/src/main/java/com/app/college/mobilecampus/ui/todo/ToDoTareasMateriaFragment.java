package com.app.college.mobilecampus.ui.todo;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.app.college.mobilecampus.R;
import com.app.college.mobilecampus.todo.Materia;
import com.app.college.mobilecampus.todo.todoUtilSQL;
import com.app.college.mobilecampus.todo.tododatabase.TodoDbHelper;
import com.app.college.mobilecampus.todo.tododatabase.TodoMateriaEntry;

import java.io.IOException;
import java.util.ArrayList;

public class ToDoTareasMateriaFragment extends Fragment {

    private Spinner materias;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.todo_tareas_materia_fragment,container,false);
        materias = view.findViewById(R.id.SortByMateria);
        final ArrayList<Materia> materias = ArrayListGetAllMateria();
        ArrayList<String> nombresMaterias = new ArrayList<String>();
        for(Materia m:materias)
            nombresMaterias.add(m.getNombre());

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, nombresMaterias);


        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        this.materias.setAdapter(arrayAdapter);

        this.materias.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                com.app.college.mobilecampus.utils.utils.showToast("pos: " + position, getContext());


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        return view;
    }

    public ArrayList<Materia> ArrayListGetAllMateria(){

        ArrayList<Materia> list_materia = new ArrayList<Materia>();
        TodoDbHelper todoDbHelper = new TodoDbHelper(getContext());
        SQLiteDatabase db;
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
        return list_materia;
    }
}
