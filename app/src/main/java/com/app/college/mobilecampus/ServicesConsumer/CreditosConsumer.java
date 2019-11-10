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

    private final static String CREDITOS_REQUEST = "https://campus-movil-255322.appspot.com/CreditosEstudiante/verCreditosPorEstudiante?id=";
    public Creditos creditos;
    private Context context;

    public CreditosConsumer (Context context){
        this.context=context;
        creditos = new Creditos();
    }

    public void loadCreditos (){
        if (utils.checkConectivity(context)){
            String URL = CREDITOS_REQUEST + 1610011255;//LoginConsumer.estudiante.getId();
            StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, (new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        JSONObject jsonObject;
                        for (int i = 0; i < jsonArray.length(); i++) {
                            jsonObject = jsonArray.getJSONObject(i);
                            if (jsonObject.getString("nombre").equals("Informática")){
                                creditos.setCompuclub(jsonObject.getInt("creditos_obtenidos"),jsonObject.getInt("cantidad"));
                            //Verificar que el nombre "investigacion" este corecto en el Json
                            } else if (jsonObject.getString("nombre").equals("Investigacion")){
                                creditos.setInvestigacion(jsonObject.getInt("creditos_obtenidos"),jsonObject.getInt("cantidad"));
                                //Verificar que el nombre "bienestar" este corecto en el Json
                            } else if (jsonObject.getString("nombre").equals("Bienestar")){
                                creditos.setBienestar(jsonObject.getInt("creditos_obtenidos"),jsonObject.getInt("cantidad"));
                            } else if (jsonObject.getString("nombre").equals("Emprendimiento")){
                                creditos.setEmprendimiento(jsonObject.getInt("creditos_obtenidos"),jsonObject.getInt("cantidad"));
                            } else if(jsonObject.getString("nombre").equals("academicos")){
                                creditos.setAcademicos(jsonObject.getInt("creditos_obtenidos"),jsonObject.getInt("cantidad"));
                            }
                        }
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
