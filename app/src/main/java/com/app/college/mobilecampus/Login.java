package com.app.college.mobilecampus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
        Toast t = Toast.makeText(this,"click",Toast.LENGTH_LONG);
        t.show();
        startActivity(new Intent(this,Inicio.class));

    }
}
