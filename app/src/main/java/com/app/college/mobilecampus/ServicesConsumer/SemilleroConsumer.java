package com.app.college.mobilecampus.ServicesConsumer;

import android.content.Context;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.app.college.mobilecampus.model.Semillero;
import com.app.college.mobilecampus.utils.utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SemilleroConsumer {

    private static String SEMILLEROS_REQUEST = "https://campus-movil-255322.appspot.com/semilleros/verSemilleros";
    public static ArrayList<Semillero> semilleros = null;
    private Context context;

    public SemilleroConsumer(Context context){
        this.context=context;
    }


    public void loadSemilleros(){
        semilleros = new ArrayList<>();

        if(utils.checkConectivity(context)){

            StringRequest stringRequest = new StringRequest(Request.Method.GET, SEMILLEROS_REQUEST, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        JSONObject jsonObject = null;
                        for(int i=0; i < jsonArray.length(); i++){
                            jsonObject = jsonArray.getJSONObject(i);
                            String status = jsonObject.getBoolean("estado")?"activo":"inactivo";
                            semilleros.add(new Semillero(jsonObject.getString("nombre"),jsonObject.getString("descripcion"),jsonObject.getString("condicion"),
                                    jsonObject.getString("profesor"),jsonObject.getString("correo_profesor"),status));
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    error.printStackTrace();

                }
            });
            RequestQueue requestQueue = Volley.newRequestQueue(context);
            requestQueue.add(stringRequest);
        }else{
            utils.showToast("Revisa la conexión a internet",context);
        }

    }
}
