package com.app.college.mobilecampus.ui.semilleros;

import androidx.lifecycle.ViewModelProviders;

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
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.app.college.mobilecampus.R;
import com.app.college.mobilecampus.ServicesConsumer.SemilleroConsumer;
import com.app.college.mobilecampus.model.Semillero;
import com.app.college.mobilecampus.utils.utils;

public class SemillerosFragment extends Fragment {

    private SemillerosViewModel mViewModel;
    private ListView listView;
    private ArrayAdapter<String> arrayAdapter;
    private ArrayAdapter<String> arrayAdapter2;

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
        semilleroConsumer = new SemilleroConsumer(getContext());
        semilleroConsumer.loadSemilleros();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {

                arrayAdapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1);
                arrayAdapter2 = new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_2);
                for(Semillero semillero: SemilleroConsumer.semilleros){
                    arrayAdapter.add(semillero.getNombre());
                    arrayAdapter2.add(semillero.getDescripcion());
                };
                listView  = (ListView) view.findViewById(R.id.listSemillero);
                listView.setAdapter(arrayAdapter);

                utils.showToast(SemilleroConsumer.semilleros.size()+"",getContext());

            }
        }, 3000);



    }
}
