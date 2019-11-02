package com.app.college.mobilecampus.ServicesConsumer;

import android.content.Context;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.app.college.mobilecampus.model.Semillero;
import com.app.college.mobilecampus.utils.utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SemilleroConsumer {

    private static String SEMILLEROS_REQUEST = "https://campus-movil-255322.appspot.com/bienestar/verCursosDeportes";
    public static ArrayList<Semillero> semilleros = null;
    private Context context;

    public SemilleroConsumer(Context context){
        this.context=context;
    }


    public void loadSemilleros(){
        semilleros = new ArrayList<>();

        if(utils.checkConectivity(context)){
            JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.GET, SEMILLEROS_REQUEST, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        JSONObject jsonObject = null;
                        utils.showToast(jsonArray.length()+"",context);

                        for(int i=0; i < jsonArray.length(); i++){
                            jsonObject = jsonArray.getJSONObject(i);
                            String status = jsonObject.getBoolean("estado")?"activo":"inactivo";
                            semilleros.add(new Semillero(jsonObject.getString("nombre"),jsonObject.getString("descripcion"),jsonObject.getString("condicion"),
                                    jsonObject.getString("profesor"),jsonObject.getString("correo_profesor"),status));
                        }
                        utils.showToast("Hay "+ jsonArray.length() +" semilleros",context);
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
            requestQueue.add(jsonRequest);
        }else{
            utils.showToast("Revisa la conexión a internet",context);
        }

    }
}
