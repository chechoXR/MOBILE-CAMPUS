package com.app.college.mobilecampus.ui.creditos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.app.college.mobilecampus.R;
import com.app.college.mobilecampus.ServicesConsumer.CreditosConsumer;

public class CreditosFragment extends Fragment {

    private CreditosViewModel creditosViewModel;

    private TextView textCreditosEmprendimiento;
    private TextView textCreditosInformatica;
    private TextView textCreditosInvestigacion;
    private TextView textCreditosBienestar;
    private ProgressBar progresoEmprendimiento;
    private ProgressBar progresoInformatica;
    private ProgressBar progresoInvestigacion;
    private ProgressBar progresoBienestar;


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        creditosViewModel = ViewModelProviders.of(this).get(CreditosViewModel.class);
        View root = inflater.inflate(R.layout.fragment_creditos, container, false);

        textCreditosEmprendimiento = root.findViewById(R.id.textCreditosEmprendimiento);
        textCreditosInformatica = root.findViewById(R.id.textCreditosInformatica);
        textCreditosInvestigacion = root.findViewById(R.id.textCreditosInvestigacion);
        textCreditosBienestar = root.findViewById(R.id.textCreditosBienestar);
        progresoEmprendimiento = root.findViewById(R.id.progresoEmprendimiento);
        progresoInformatica = root.findViewById(R.id.progresoInformatica);
        progresoInvestigacion = root.findViewById(R.id.progresoInvestigacion);
        progresoBienestar = root.findViewById(R.id.progresoBienestar);

        CreditosConsumer consumer = new CreditosConsumer(this.getContext());
        consumer.loadCreditos();
        textCreditosEmprendimiento.setText(consumer.creditos.getEmprendimiento()[0] + " de " + consumer.creditos.getEmprendimiento()[1]);
        textCreditosInformatica.setText(consumer.creditos.getCompuclub()[0] + " de " + consumer.creditos.getCompuclub()[1]);
        textCreditosInvestigacion.setText(consumer.creditos.getInvestigacion()[0] + " de " + consumer.creditos.getInvestigacion()[1]);
        textCreditosBienestar.setText(consumer.creditos.getBienestar()[0] + " de " + consumer.creditos.getBienestar()[1]);

        progresoEmprendimiento.setMax(consumer.creditos.getEmprendimiento()[1]);
        progresoEmprendimiento.setProgress(consumer.creditos.getEmprendimiento()[0]);
        progresoInformatica.setMax(consumer.creditos.getCompuclub()[1]);
        progresoInformatica.setProgress(consumer.creditos.getCompuclub()[0]);
        progresoInvestigacion.setMax(consumer.creditos.getInvestigacion()[1]);
        progresoInvestigacion.setProgress(consumer.creditos.getInvestigacion()[0]);
        progresoBienestar.setMax(consumer.creditos.getBienestar()[1]);
        progresoBienestar.setProgress(consumer.creditos.getBienestar()[0]);

        return root;
    }
}




















