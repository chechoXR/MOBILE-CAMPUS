package com.app.college.mobilecampus;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class Login extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
/*
        Button btn = findViewById(R.id.boton_ingresar);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (v.getContext(),Inicio.class);
                startActivity(intent);
            }
        });
*/
    }

    public void click (View view){

        startActivity(new Intent(this,MainActivity.class));

    }

}
