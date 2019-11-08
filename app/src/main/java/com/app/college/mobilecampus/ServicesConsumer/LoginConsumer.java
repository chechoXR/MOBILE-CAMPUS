
package com.app.college.mobilecampus.ServicesConsumer;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.app.college.mobilecampus.MainActivity;
import com.app.college.mobilecampus.Login;
import com.app.college.mobilecampus.model.Estudiante;
import com.app.college.mobilecampus.utils.utils;
import com.app.college.mobilecampus.session.sessiondatabase.UserSessionDbHelper;
import com.app.college.mobilecampus.session.sessiondatabase.UserSessionEntry;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginConsumer {

    private static String LOGIN_REQUEST = "https://campus-movil-255322.appspot.com/login/estudiante";
    public static Estudiante estudiante = new Estudiante(null,null,null,null, null);
    private static Context context;


    public LoginConsumer(Context context){
        this.context=context;
    }




    public void loginRequest(final String email, final String password, final Context context) {

        if (utils.checkConectivity(context)) {

            StringRequest stringRequest = new StringRequest(Request.Method.POST, LOGIN_REQUEST,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                Log.d("Response",response);
                                JSONObject jsonObject = new JSONObject(response);
                                if(!jsonObject.isNull("nombre")) {
                                    String nombre = jsonObject.getString("nombre");
                                    String apellido = jsonObject.getString("apellido");
                                    String email = jsonObject.getString("email");
                                    String usuario = jsonObject.getString("usuario");
                                    String id = jsonObject.getString("id");
                                    estudiante = new Estudiante(nombre, apellido, email, usuario, id);
                                    utils.showToast("Bienvenido " + nombre,context.getApplicationContext());
                                    nextStep(true, context);
                                }
                            } catch (JSONException e) {
                                nextStep(false , context);
                                utils.showToast("Error de autenticacion",context.getApplicationContext());
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    utils.showToast("Error en la peticion",context.getApplicationContext());
                    nextStep(false , context);
                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> params = new HashMap<>();
                    params.put("usuario",email);
                    params.put("password",password);
                    return params;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(context);
            requestQueue.add(stringRequest);
        } else {
            utils.showToast("Error de conexión, verifica conexión a internet ",context.getApplicationContext());
            nextStep(false , context);
        }
    }

    public static void nextStep(boolean success, Context context){

        try {
            if(success){
                startSession(context.getApplicationContext());
                Intent intent = new Intent(context, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("nombre",estudiante.getNombre());
                intent.putExtra("apellido",estudiante.getApellido());
                intent.putExtra("usuario",estudiante.getUsuario());
                intent.putExtra("correo",estudiante.getCorreo());
                intent.putExtra("id", estudiante.getId());
                context.startActivity(intent);
            }else{
                Login.resetLogin();
            }
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            utils.showToast("Error interno",context.getApplicationContext());
        }
    }

    private static void startSession(Context context) {
        UserSessionDbHelper session = new UserSessionDbHelper(context);
        SQLiteDatabase db = session.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(UserSessionEntry.USER,estudiante.getUsuario());
        contentValues.put(UserSessionEntry.NAME,estudiante.getNombre());
        contentValues.put(UserSessionEntry.LASTNAME,estudiante.getApellido());
        contentValues.put(UserSessionEntry.EMAIL,estudiante.getCorreo());
        contentValues.put(UserSessionEntry.ID, estudiante.getId());
        contentValues.put(UserSessionEntry.ACTIVE,"1");

        db.insert(UserSessionEntry.TABLE_NAME,null,contentValues);
    }

    public static Intent finishSession(Context context) {
        UserSessionDbHelper session = new UserSessionDbHelper(context);
        SQLiteDatabase db = session.getWritableDatabase();
        db.delete(UserSessionEntry.TABLE_NAME,UserSessionEntry.EMAIL +"= '" + estudiante.getCorreo()+"'" ,null);
        estudiante.setNombre(null);
        estudiante.setApellido(null);
        estudiante.setCorreo(null);
        estudiante.setUsuario(null);

        return new Intent(context,Login.class);
    }




}
