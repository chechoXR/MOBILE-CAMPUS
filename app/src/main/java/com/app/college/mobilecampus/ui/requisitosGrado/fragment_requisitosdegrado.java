package com.app.college.mobilecampus.ui.requisitosGrado;

import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.app.college.mobilecampus.R;
import com.app.college.mobilecampus.ServicesConsumer.CreditosConsumer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class fragment_requisitosdegrado extends Fragment {

    private FragmentRequisitosdegradoViewModel mViewModel;

    private TextView textCreditos;
    private ProgressBar progressBar;
    private CreditosConsumer consumer;

    ExpandableListView expandableListView;
    List<String> listGroup;
    HashMap<String, List<String>> listItem;
    RequisitosAdapter requisitosAdapter;


    public static fragment_requisitosdegrado newInstance() {
        return new fragment_requisitosdegrado();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {
        mViewModel = ViewModelProviders.of(this).get(FragmentRequisitosdegradoViewModel.class);
        View root = inflater.inflate(R.layout.fragment_requisitosdegrado, container, false);

        textCreditos = root.findViewById(R.id.textCreditosTotales);
        progressBar = root.findViewById(R.id.progresoCreditosTotales);

        int creditosTotalesRealizados = CreditosConsumer.creditos.getAcademicos()[0] +
                                        CreditosConsumer.creditos.getEmprendimiento()[0] +
                                        CreditosConsumer.creditos.getCompuclub()[0] +
                                        CreditosConsumer.creditos.getInvestigacion()[0];
        int creditosTotalesRequeridos = CreditosConsumer.creditos.getAcademicos()[1] +
                                        CreditosConsumer.creditos.getEmprendimiento()[1] +
                                        CreditosConsumer.creditos.getCompuclub()[1] +
                                        CreditosConsumer.creditos.getInvestigacion()[1];

        textCreditos.setText("Creditos totales "+creditosTotalesRealizados+" de "+creditosTotalesRequeridos);
        progressBar.setMax(creditosTotalesRequeridos);
        progressBar.setProgress(creditosTotalesRealizados);

        consumer = new CreditosConsumer(getContext());
        consumer.loadCreditos();

        expandableListView = (ExpandableListView) root.findViewById(R.id._dynamic);
        listGroup = new ArrayList<>();
        listItem = new HashMap<>();
        initData ();
        requisitosAdapter = new RequisitosAdapter((Context) getActivity(), listGroup, listItem);
        expandableListView.setAdapter(requisitosAdapter);


        return root;
    }

    private void initData() {
        listGroup.add(getString(R.string.group1));
        listGroup.add(getString(R.string.group2));
        listGroup.add(getString(R.string.group3));

        String []array;

        List<String> list1 = new ArrayList<>();
        array = getResources().getStringArray(R.array.group1);
        for (String item: array) {
            list1.add(item);
        }

        List<String> list2 = new ArrayList<>();
        array = getResources().getStringArray(R.array.group2);
        for (String item: array) {
            list2.add(item);
        }

        List<String> list3 = new ArrayList<>();
        array = getResources().getStringArray(R.array.group3);
        for (String item: array) {
            list3.add(item);
        }

        listItem.put(listGroup.get(0), list1);
        listItem.put(listGroup.get(1), list2);
        listItem.put(listGroup.get(2), list3);

    }



}
