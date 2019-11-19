package com.app.college.mobilecampus;

import androidx.appcompat.app.AppCompatActivity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.app.college.mobilecampus.todo.Materia;
import com.app.college.mobilecampus.todo.todoUtilSQL;
import com.app.college.mobilecampus.todo.tododatabase.TodoDbHelper;
import com.app.college.mobilecampus.todo.tododatabase.TodoMateriaEntry;
import com.app.college.mobilecampus.ui.dialog.DatePickerFragment;
import com.app.college.mobilecampus.ui.todo.ToDoHomeFragment;
import com.app.college.mobilecampus.utils.utils;

import java.io.IOException;
import java.util.ArrayList;

public class ToDoAdd extends AppCompatActivity {
    private EditText nombre;
    private EditText descripcion;
    private EditText fecha_ini;
    private EditText fecha_fin;
    private Spinner materia;
    private Button añadir;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do_add);
        nombre=findViewById(R.id.nombreMateria);
        descripcion=findViewById(R.id.descTarea);
        fecha_fin = findViewById(R.id.fechaFin);
        fecha_ini = findViewById(R.id.fechaIni);
        materia = findViewById(R.id.materia);
        ArrayList<Materia> materias = ArrayListGetAllMateria();
        ArrayList<String> nombresMaterias = new ArrayList<String>();
        for(Materia m:materias)
            nombresMaterias.add(m.getNombre());

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, nombresMaterias);


        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        materia.setAdapter(arrayAdapter);

        fecha_ini.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog(fecha_ini);
            }
        });
        Button añadir = findViewById(R.id.añadir_tarea);
        añadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                añadir();
            }
        });


        fecha_fin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog(fecha_fin);
            }
        });
    }
    private void añadir(){
        TodoDbHelper todoDbHelper = new TodoDbHelper(this);
        SQLiteDatabase db = todoDbHelper.getWritableDatabase();
        todoUtilSQL todoutilSQL = new todoUtilSQL(db);

        Cursor c = todoutilSQL.getIdMateria();
        int id=0;
        if(c!=null && c.getColumnCount()!=0) {
            try {
                c.moveToFirst();
                while (!c.isAfterLast()) {
                    id = c.getInt(c.getColumnIndex(TodoMateriaEntry.ID));
                }
            } catch (Exception e) {
                utils.showToast("FALTAN DATOS", this);
            }
        }
        //Calificación = -1, significa que no se ha agregado nota a la tarea
        //Completada = 0, significa que no se ha completado, por otro lado 1 significa que ha sido completada
        if(nombre.getText().toString().trim().equals("") || fecha_ini.getText().toString().trim().equals(""))
            utils.showToast("Datos incompletos",this);
        else{
            ArrayList<Materia> materias = ArrayListGetAllMateria();
            int mat_id=0;
            for(int i=0; i< materias.size();i++)
                if(((String)materia.getSelectedItem()).equals(materias.get(i).getNombre()))
                    mat_id = materias.get(i).getId();

            todoutilSQL.insertTarea( nombre.getText().toString(),descripcion.getText().toString(),fecha_ini.getText().toString(),fecha_fin.getText().toString(),
                 -1,mat_id,0);
            utils.showToast("TAREA AGREGADA ",this);
        }
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void showDatePickerDialog(final EditText editText) {
        DatePickerFragment newFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                // +1 because January is zero
                final String selectedDate = day + " / " + (month+1) + " / " + year;
                editText.setText(selectedDate);
            }
        });

        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public ArrayList<Materia> ArrayListGetAllMateria(){

        ArrayList<Materia> list_materia = new ArrayList<Materia>();
        TodoDbHelper todoDbHelper = new TodoDbHelper(this);
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


