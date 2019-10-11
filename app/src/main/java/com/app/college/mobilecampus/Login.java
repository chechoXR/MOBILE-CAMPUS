package com.app.college.mobilecampus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.app.college.mobilecampus.Model.Estudiante;
import com.app.college.mobilecampus.ServicesConsumer.LoginConsumer;

public class Login extends AppCompatActivity {

    private EditText username,password;
    private Button submit;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = (EditText) findViewById(R.id.usuario);
        password = (EditText) findViewById(R.id.contrasena);
        submit = (Button) findViewById(R.id.boton_ingresar);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        progressBar.setVisibility(View.GONE);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkUser(username.getText().toString().trim(),password.getText().toString().trim());

                submit.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
                password.setText("");


            }
        });
    }

        private void checkUser(String username, String password){

        submit.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.VISIBLE);
            LoginConsumer loginConsumer = new LoginConsumer(this);
            if(!username.equals("") && !password.equals("")){
                loginConsumer.loginRequest(username,password);
            }else {
                submit.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
                this.password.setText("");
            }

        }
}
