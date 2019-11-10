package com.app.college.mobilecampus;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.app.college.mobilecampus.ServicesConsumer.LoginConsumer;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import android.view.MenuItem;
import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import com.google.android.material.navigation.NavigationView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    public static FloatingActionButton fab;
    private static long lastMovementFloatingButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Snackbar.make(view, "Hipervinculo a Moodle ->", Snackbar.LENGTH_LONG)
                  //      .setAction("Action", null).show();
                Uri uri = Uri.parse("https://elearn.poligran.edu.co");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        DrawerLayout drawer = findViewById(R.id.drawer_layout);

        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_calendario, R.id.nav_bienestar,
                R.id.nav_creditos, R.id.nav_semilleros, R.id.nav_horario, R.id.nav_todo,
                R.id.nav_calcular_nota, R.id.nav_requisitos)
                .setDrawerLayout(drawer)
                .build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        lastMovementFloatingButton=System.currentTimeMillis();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action__materia_add:
                Intent intentTarea = new Intent(MainActivity.this, MateriaAdd.class);
                startActivity(intentTarea);
                return true;
            case R.id.action_tarea_add:
                Intent intentToDo = new Intent(MainActivity.this, ToDoAdd.class);
                startActivity(intentToDo);
                return true;
            case R.id.action_settings:
                Intent intent = new Intent(MainActivity.this,Settings.class);
                startActivity(intent);
                return true;
            case R.id.action_logout:
                Intent intent1 = LoginConsumer.finishSession(getApplicationContext());
                intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent1);
                finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public void calendario (View view){

        Navigation.findNavController(view).navigate(R.id.action_home_to_calendario);
    }

    public void bienestar (View view){

        Navigation.findNavController(view).navigate(R.id.action_home_to_bienestar);
    }

    public void creditos (View view){

        Navigation.findNavController(view).navigate(R.id.action_home_to_creditos);
    }

    public void semilleros (View view){

        Navigation.findNavController(view).navigate(R.id.action_home_to_semilleros);
    }

    public void horario (View view){

        Navigation.findNavController(view).navigate(R.id.action_home_to_horario);
    }

    public void requisitos (View view){

        Navigation.findNavController(view).navigate(R.id.action_home_to_requisitos);
    }


    public void todo(View view){
        Navigation.findNavController(view).navigate(R.id.nav_todo);
    }

    public static void moveFloatingButton(float Y, long time) {


        if (time - lastMovementFloatingButton > 1000) {
            fab.setTranslationY(Y);

            lastMovementFloatingButton = System.currentTimeMillis();
        }

    }
    
        public void calcularNota (View view){
            Navigation.findNavController(view).navigate(R.id.nav_calcular_nota);
        }

    }