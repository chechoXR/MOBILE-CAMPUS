package com.app.college.mobilecampus.ServicesConsumer;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.app.college.mobilecampus.model.Creditos;
import com.app.college.mobilecampus.model.Estudiante;
import com.app.college.mobilecampus.session.sessiondatabase.UserSessionEntry;
import com.app.college.mobilecampus.utils.utils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CreditosConsumer {

    private static String CREDITOS_REQUEST = "https://campus-movil-255322.appspot.com/CreditosEstudiante/verCreditosPorEstudiante?id=1610011255";
    public static String id;
    private Context context;

    public CreditosConsumer (Context context){this.context=context;}

    public void loadCreditos (){
        if (utils.checkConectivity(context)){
            StringRequest stringRequest;
            stringRequest = new StringRequest(Request.Method.GET, CREDITOS_REQUEST, (new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {

                        JSONObject jsonObject = new JSONObject(response);
                        String status = jsonObject.getBoolean("estado")?"activo":"inactivo";



                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }), new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    error.printStackTrace();
                }
            });
            RequestQueue requestQueue = Volley.newRequestQueue(context);
            requestQueue.add(stringRequest);
        } else {
            utils.showToast("Revisa la conexión a internet",context);
        }

    }

}
