package com.app.college.mobilecampus;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Inicio extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        Button btnSem = findViewById(R.id.button_consultaSem);
        Button btnCal = findViewById(R.id.button_calendarioAcademico);
        Button btnAcerca = findViewById(R.id.button_acerca_de_la_app);

        btnSem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), Semilleros.class);
                startActivity(intent);
            }
        });
        btnCal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                Calendario c1 = new Calendario();
                fragmentTransaction.add(R.id.fragment_container, c1);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        btnAcerca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AcercaDeLaApp acercaApp = new AcercaDeLaApp();
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.add(R.id.fragment_acerca_de_la_app, acercaApp);
                ft.addToBackStack(null);
                ft.commit();
            }
        });
    }

}
