package com.app.college.mobilecampus.ui.bienestar;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.app.college.mobilecampus.R;
import com.app.college.mobilecampus.ServicesConsumer.BienestarConsumer;
import com.app.college.mobilecampus.ServicesConsumer.SemilleroConsumer;
import com.app.college.mobilecampus.ui.semilleros.SemilleroListVIew;
import com.app.college.mobilecampus.utils.utils;

public class BienestarFragment extends Fragment {

    private BienestarViewModel bienestarViewModel;
    private ListView listView;
    private TextView textView;
    private BienestarConsumer bienestarConsumer;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        bienestarViewModel = ViewModelProviders.of(this).get(BienestarViewModel.class);
        View root = inflater.inflate(R.layout.fragment_bienestar, container, false);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        listView  = (ListView) view.findViewById(R.id.listCurso);
        textView = view.findViewById(R.id.nombre_curso);
        bienestarConsumer = new BienestarConsumer(getContext());
        bienestarConsumer.loadCursos();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                if(BienestarConsumer.bienestar!=null){
                    BienestarListView bienestarListView = new BienestarListView(getActivity(),BienestarConsumer.bienestar);
                    listView.setAdapter(bienestarListView);
                }

            }
        }, 2500);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                textView.setTextColor(Color.parseColor("#0f385a"));
                textView.setText(BienestarConsumer.bienestar.get(i).getNombre());
            }
        });

    }


}