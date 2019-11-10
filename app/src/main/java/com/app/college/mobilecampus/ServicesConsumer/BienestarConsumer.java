package com.app.college.mobilecampus.ServicesConsumer;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.app.college.mobilecampus.model.Bienestar;
import com.app.college.mobilecampus.model.Semillero;
import com.app.college.mobilecampus.utils.utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class BienestarConsumer {

    private static String BIENESTAR_REQUEST = "https://campus-movil-255322.appspot.com/bienestar/verCursos";
    public static ArrayList<Bienestar> bienestar = null;
    private Context context;

    public BienestarConsumer(Context context){
        this.context=context;
    }

    public void loadCursos(){
        bienestar = new ArrayList<>();

        if(utils.checkConectivity(context)){
            StringRequest stringRequest = new StringRequest(Request.Method.GET, BIENESTAR_REQUEST, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        JSONObject jsonObject;
                        for(int i=0; i < jsonArray.length(); i++){
                            jsonObject = jsonArray.getJSONObject(i);
                            bienestar.add(new Bienestar(jsonObject.getString("nombre"),jsonObject.getString("modalidad"),jsonObject.getString("descripcion"),
                                    jsonObject.getString("correo"),jsonObject.getString("tipo"),jsonObject.getString("profresor")));
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
