package com.app.college.mobilecampus;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class AcercaDeLaApp extends Fragment {

    public AcercaDeLaApp (){

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_acerca_de_la_app, container, false);
    }
}
