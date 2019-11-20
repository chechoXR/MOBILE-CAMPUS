package com.app.college.mobilecampus.ui.semilleros;

import androidx.lifecycle.ViewModelProviders;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.app.college.mobilecampus.R;
import com.app.college.mobilecampus.ServicesConsumer.SemilleroConsumer;
import com.app.college.mobilecampus.model.Semillero;
import com.app.college.mobilecampus.utils.utils;

public class SemillerosFragment extends Fragment {

    private SemillerosViewModel mViewModel;
    private ListView listView;
    private TextView textView;
    private SemilleroConsumer semilleroConsumer;


    public static SemillerosFragment newInstance() {
        return new SemillerosFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {


        return inflater.inflate(R.layout.semilleros_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(SemillerosViewModel.class);
    }


    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        listView  = (ListView) view.findViewById(R.id.listSemillero);
        textView = view.findViewById(R.id.nombre_semillero);
        semilleroConsumer = new SemilleroConsumer(getContext());
        semilleroConsumer.loadSemilleros();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                if(SemilleroConsumer.semilleros!=null) {
                    SemilleroListVIew semilleroListVIew = new SemilleroListVIew(getActivity(), SemilleroConsumer.semilleros);
                    listView.setAdapter(semilleroListVIew);
                }

            }
        }, 2500);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                textView.setTextColor(Color.parseColor("#0f385a"));
                textView.setText(SemilleroConsumer.semilleros.get(i).getNombre());
            }
        });

    }
}
