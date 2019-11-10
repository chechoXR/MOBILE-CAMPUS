package com.app.college.mobilecampus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.app.college.mobilecampus.todo.todoUtilSQL;
import com.app.college.mobilecampus.todo.tododatabase.TodoDbHelper;
import com.app.college.mobilecampus.utils.utils;

public class MateriaAdd extends AppCompatActivity {
    public EditText materia;
    public Button añadir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materia_add);
        materia=findViewById(R.id.nombreMateria);
        añadir=findViewById(R.id.añadir_materia);
        añadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                añadirMateria();
            }
        });
    }
    private void añadirMateria(){
        TodoDbHelper todoDbHelper = new TodoDbHelper(this);
        SQLiteDatabase db = todoDbHelper.getWritableDatabase();
        todoUtilSQL todoutilSQL = new todoUtilSQL(db);
        todoutilSQL.insertMateria(materia.getText().toString());
        utils.showToast("MATERIA AGREGADA",this);
        Intent intent = new Intent(this, ToDoAdd.class);
        startActivity(intent);
    }
}
