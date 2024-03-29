package com.app.college.mobilecampus.ServicesConsumer;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.app.college.mobilecampus.model.Creditos;
import com.app.college.mobilecampus.model.Estudiante;
import com.app.college.mobilecampus.session.UserSession;
import com.app.college.mobilecampus.session.sessiondatabase.UserSessionEntry;
import com.app.college.mobilecampus.utils.utils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CreditosConsumer {

    private final static String CREDITOS_REQUEST = "https://campus-movil-255322.appspot.com/CreditosEstudiante/verCreditosPorEstudiante?id=";
    public static Creditos creditos = new Creditos();
    private Context context;


    public CreditosConsumer (Context context){
        this.context=context;
    }

    public void loadCreditos (){
        if (utils.checkConectivity(context)){
            String URL = CREDITOS_REQUEST + LoginConsumer.getCurrentSession(context).getCodigo();
            StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, (new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        JSONObject jsonObjectCredito;
                        JSONObject jsonObject;
                        for (int i = 0; i < jsonArray.length(); i++) {
                            jsonObjectCredito = jsonArray.getJSONObject(i);
                            jsonObject = jsonObjectCredito.getJSONObject("credito");
                            if (jsonObject.getString("nombre").equals("Informática")){
                                creditos.setCompuclub(jsonObjectCredito.getInt("creditos_obtenidos"),jsonObject.getInt("cantidad"));
                            } else if (jsonObject.getString("nombre").equals("Investigación")){
                                creditos.setInvestigacion(jsonObjectCredito.getInt("creditos_obtenidos"),jsonObject.getInt("cantidad"));
                            } else if (jsonObject.getString("nombre").equals("Bienestar")){
                                creditos.setBienestar(jsonObjectCredito.getInt("creditos_obtenidos"),jsonObject.getInt("cantidad"));
                            } else if (jsonObject.getString("nombre").equals("Emprendimiento")){
                                creditos.setEmprendimiento(jsonObjectCredito.getInt("creditos_obtenidos"),jsonObject.getInt("cantidad"));
                            } else if(jsonObject.getString("nombre").equals("academicos")){
                                creditos.setAcademicos(jsonObjectCredito.getInt("creditos_obtenidos"),jsonObject.getInt("cantidad"));
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
