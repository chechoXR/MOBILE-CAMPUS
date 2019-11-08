package com.app.college.mobilecampus;

import androidx.appcompat.app.AppCompatActivity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.app.college.mobilecampus.todo.todoUtilSQL;
import com.app.college.mobilecampus.todo.tododatabase.TodoDbHelper;
import com.app.college.mobilecampus.todo.tododatabase.TodoMateriaEntry;
import com.app.college.mobilecampus.ui.dialog.DatePickerFragment;
import com.app.college.mobilecampus.ui.todo.ToDoHomeFragment;
import com.app.college.mobilecampus.utils.utils;

public class ToDoAdd extends AppCompatActivity {
    private EditText nombre;
    private EditText descripcion;
    private EditText fecha_ini;
    private EditText fecha_fin;
    private EditText materia;
    private EditText calificacion;
    private EditText completado;
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
        calificacion=findViewById(R.id.calificacion);
        completado=findViewById(R.id.completado);
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
        todoutilSQL.insertTarea( nombre.getText().toString(),descripcion.getText().toString(),fecha_ini.getText().toString(),fecha_fin.getText().toString(),
                 Double.parseDouble(calificacion.getText().toString()),id,Integer.parseInt(completado.getText().toString()));
        utils.showToast("TAREA AGREGADA",this);
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
}


