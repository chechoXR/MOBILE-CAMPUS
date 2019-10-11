package com.app.college.mobilecampus.ServicesConsumer;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.app.college.mobilecampus.MainActivity;
import com.app.college.mobilecampus.ui.home.*;
import com.app.college.mobilecampus.Model.Estudiante;
import com.app.college.mobilecampus.ui.home.HomeFragment;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginConsumer {

    private static String LOGIN_REQUEST = "https://campus-movil-255322.appspot.com/login/estudiante";
    public   Estudiante estudiante;
    private Context context;


    public LoginConsumer(Context context){
        this.context=context;
    }

    private  boolean checkConectivity(){
        boolean connected = false;
        ConnectivityManager connectivityManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            //we are connected to a network
            connected = true;
        }
        else
            connected = false;
        return connected;
    }

    public void loginRequest(final String email, final String password) {

        if (checkConectivity()) {

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
                                    estudiante = new Estudiante(nombre, apellido, email, usuario);
                                    Toast.makeText(context.getApplicationContext(), "Bienvenido " + nombre, Toast.LENGTH_LONG).show();
                                    Intent intent = new Intent(context, MainActivity.class);
                                    context.startActivity(intent);
                                }
                            } catch (JSONException e) {
                                Toast.makeText(context.getApplicationContext(), "Error de autenticacion", Toast.LENGTH_LONG).show();
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(context.getApplicationContext(), "Error en la peticion", Toast.LENGTH_LONG).show();
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
            Toast.makeText(context.getApplicationContext(), "Error de conexión ", Toast.LENGTH_LONG).show();
        }

    }
}
