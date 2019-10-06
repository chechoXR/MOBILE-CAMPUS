package com.app.college.mobilecampus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class Semilleros extends AppCompatActivity {
private ArrayList<String> arrayList;
private ArrayAdapter<String>adapter;
private ListView list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_semilleros);
        list=findViewById(R.id.listSemillero);
        arrayList=new ArrayList<String>();
        arrayList.add("Computacion avanzada para ciencias y ingenieria");
        arrayList.add("Dotomotica y automatizacion");
        arrayList.add("Diseño de empaques");
        arrayList.add("Rock y politica");
        arrayList.add("Literatura inglesa");
        adapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_item,arrayList);
        list.setAdapter(adapter);
    }
}
