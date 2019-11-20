package com.app.college.mobilecampus;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import com.app.college.mobilecampus.ServicesConsumer.LoginConsumer;
import com.app.college.mobilecampus.model.Estudiante;
import com.app.college.mobilecampus.session.sessiondatabase.UserSessionDbHelper;
import com.app.college.mobilecampus.session.sessiondatabase.UserSessionEntry;

public class Login extends AppCompatActivity {

    private static EditText username,password;
    private static Button submit;
    private static ProgressBar progressBar;
    private Estudiante estudiante_;

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
         estudiante_ = new Estudiante(null,null,null,null,null);
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
                        estudiante_.setUsuario(c.getString(c.getColumnIndex(UserSessionEntry.USER)));
                        estudiante_.setApellido(c.getString(c.getColumnIndex(UserSessionEntry.LASTNAME)));
                        estudiante_.setCorreo(c.getString(c.getColumnIndex(UserSessionEntry.EMAIL)));
                        estudiante_.setNombre(c.getString(c.getColumnIndex(UserSessionEntry.NAME)));
                        estudiante_.setCodigo(c.getString(c.getColumnIndex(UserSessionEntry.CODIGO)));
                    }else{
                        return false;
                    }
                    return true;
                }
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }

    @Override
    protected void onStart() {
        super.onStart();
        estudiante_= LoginConsumer.getCurrentSession(this);
        if(estudiante_ != null){

            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.putExtra("nombre",estudiante_.getNombre());
            intent.putExtra("apellido",estudiante_.getApellido());
            intent.putExtra("usuario",estudiante_.getUsuario());
            intent.putExtra("correo",estudiante_.getCorreo());
            intent.putExtra("codigo",estudiante_.getCodigo());
            Log.i("Estudiante onstart", estudiante_.getCodigo());
            this.startActivity(intent);
        }

        return;
    }
}
