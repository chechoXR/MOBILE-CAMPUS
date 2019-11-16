package com.app.college.mobilecampus.ui.horario;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.app.college.mobilecampus.R;
import com.app.college.mobilecampus.ServicesConsumer.LoginConsumer;
import com.app.college.mobilecampus.model.Bienestar;
import com.app.college.mobilecampus.model.Estudiante;
import com.app.college.mobilecampus.utils.utils;
import com.github.barteksc.pdfviewer.PDFView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class HorarioFragment extends Fragment {

    private HorarioViewModel mViewModel;
    private final String HORARIO_REQUEST  = "http://campus-movil-255322.appspot.com/horario";
    private byte[] horario_pdf;
    private PDFView pdfView;

    public static HorarioFragment newInstance() {
        return new HorarioFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.horario_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(HorarioViewModel.class);
        getPDFBytes(LoginConsumer.getCurrentSession(getContext()).getCodigo());
        pdfView = getActivity().findViewById(R.id.pdfView);

    }

    private void getPDFBytes(final String codigo_estudiante) {
        {
            if(utils.checkConectivity(getContext())) {
                BinaryRequest binaryRequest = new BinaryRequest(Request.Method.GET, HORARIO_REQUEST + "?codigo_estudiante=" + codigo_estudiante, new Response.Listener<byte[]>() {
                    @Override
                    public void onResponse(byte[] response) {
                        horario_pdf = response;
                        setHorario();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
                RequestQueue requestQueue = Volley.newRequestQueue(getContext());
                requestQueue.add(binaryRequest);
            }else{
                utils.showToast("Revisa la conexión a internet",getContext());
            }

        }

    }

  public void setHorario(){

        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                pdfView.fromBytes(horario_pdf)
                        .pages(0, 2, 1, 3, 3, 3) // all pages are displayed by default
                        .enableSwipe(false) // allows to block changing pages using swipe
                        .swipeHorizontal(false)
                        .enableDoubletap(true)
                        .defaultPage(0)
                        .enableAnnotationRendering(false) // render annotations (such as comments, colors or forms)
                        .password(null)
                        .scrollHandle(null)
                        .enableAntialiasing(true) // improve rendering a little bit on low-res screens
                        .spacing(0)
                        .load();
            }
        });


  }
}
