package com.app.college.mobilecampus;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.app.college.mobilecampus.Model.Estudiante;
import com.app.college.mobilecampus.ServicesConsumer.LoginConsumer;
import com.app.college.mobilecampus.session.UserSession;
import com.app.college.mobilecampus.session.database.UserSessionDbHelper;
import com.app.college.mobilecampus.session.database.UserSessionEntry;
import com.app.college.mobilecampus.session.database.UserSessionQueryHelper;

import java.util.Arrays;

public class Login extends AppCompatActivity {

    private static EditText username,password;
    private static Button submit;
    private static ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //Verifica si el usuario se encuentra logeado.
        if(checkSession())LoginConsumer.nextStep(true,this);
        username = (EditText) findViewById(R.id.usuario);
        password = (EditText) findViewById(R.id.contrasena);
        submit = (Button) findViewById(R.id.boton_ingresar);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        progressBar.setVisibility(View.GONE);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkUser(username.getText().toString().trim(),password.getText().toString().trim());
            }
        });
    }

    private void checkUser(String username, String password){

        submit.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.VISIBLE);
        LoginConsumer loginConsumer = new LoginConsumer(this);
        if(!username.equals("") && !password.equals("")){
            loginConsumer.loginRequest(username,password,this);
        }else {
            submit.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);
            this.password.setText("");
        }
    }

    public static void resetLogin(){
        username.setText("");
        password.setText("");
        submit.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
    }

    private boolean checkSession(){
        UserSessionDbHelper session = new UserSessionDbHelper(this.getApplicationContext());
        SQLiteDatabase db = session.getReadableDatabase();
        //Retorna todos los datos que se encuentran en la base de datos
        Cursor c = db.rawQuery("SELECT * FROM "+UserSessionEntry.TABLE_NAME,null);
        if(c!=null && c.getColumnCount()!=0) {
            try {
                c.moveToFirst();
                while (!c.isAfterLast()) {
                    int isActive = Integer.parseInt(c.getString(c.getColumnIndex(UserSessionEntry.ACTIVE)));
                    if (isActive == 1) {
                        LoginConsumer.estudiante.setUsuario(c.getString(c.getColumnIndex(UserSessionEntry.USER)));
                        LoginConsumer.estudiante.setApellido(c.getString(c.getColumnIndex(UserSessionEntry.LASTNAME)));
                        LoginConsumer.estudiante.setCorreo(c.getString(c.getColumnIndex(UserSessionEntry.EMAIL)));
                        LoginConsumer.estudiante.setNombre(c.getString(c.getColumnIndex(UserSessionEntry.NAME)));
                        return true;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }


}
