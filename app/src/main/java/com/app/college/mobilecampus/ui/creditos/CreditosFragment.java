package com.app.college.mobilecampus.ui.creditos;

import android.os.Bundle;
import android.os.Handler;
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
import com.app.college.mobilecampus.model.Creditos;

public class CreditosFragment extends Fragment {

    private CreditosViewModel creditosViewModel;

    private TextView textCreditosAcademicos;
    private TextView textCreditosEmprendimiento;
    private TextView textCreditosInformatica;
    private TextView textCreditosInvestigacion;
    private TextView textCreditosBienestar;
    private ProgressBar progresoAcademicos;
    private ProgressBar progresoEmprendimiento;
    private ProgressBar progresoInformatica;
    private ProgressBar progresoInvestigacion;
    private ProgressBar progresoBienestar;
    private CreditosConsumer consumer;


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        creditosViewModel = ViewModelProviders.of(this).get(CreditosViewModel.class);
        View root = inflater.inflate(R.layout.fragment_creditos, container, false);

        textCreditosAcademicos = root.findViewById(R.id.textCreditosAcademicos);
        textCreditosEmprendimiento = root.findViewById(R.id.textCreditosEmprendimiento);
        textCreditosInformatica = root.findViewById(R.id.textCreditosInformatica);
        textCreditosInvestigacion = root.findViewById(R.id.textCreditosInvestigacion);
        textCreditosBienestar = root.findViewById(R.id.textCreditosBienestar);
        progresoAcademicos = root.findViewById(R.id.progresoAcademicos);
        progresoEmprendimiento = root.findViewById(R.id.progresoEmprendimiento);
        progresoInformatica = root.findViewById(R.id.progresoInformatica);
        progresoInvestigacion = root.findViewById(R.id.progresoInvestigacion);
        progresoBienestar = root.findViewById(R.id.progresoBienestar);
        consumer = new CreditosConsumer(getContext());
        consumer.loadCreditos();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                textCreditosAcademicos.setText(CreditosConsumer.creditos.getAcademicos()[0] + " de "+ CreditosConsumer.creditos.getAcademicos()[1]);
                textCreditosEmprendimiento.setText(CreditosConsumer.creditos.getEmprendimiento()[0] + " de " + CreditosConsumer.creditos.getEmprendimiento()[1]);
                textCreditosInformatica.setText(CreditosConsumer.creditos.getCompuclub()[0] + " de " + CreditosConsumer.creditos.getCompuclub()[1]);
                textCreditosInvestigacion.setText(CreditosConsumer.creditos.getInvestigacion()[0] + " de " + CreditosConsumer.creditos.getInvestigacion()[1]);
                textCreditosBienestar.setText(CreditosConsumer.creditos.getBienestar()[0] + " de " + CreditosConsumer.creditos.getBienestar()[1]);

                progresoAcademicos.setMax(CreditosConsumer.creditos.getAcademicos()[1]);
                progresoAcademicos.setProgress(CreditosConsumer.creditos.getAcademicos()[0]);
                progresoEmprendimiento.setMax(CreditosConsumer.creditos.getEmprendimiento()[1]);
                progresoEmprendimiento.setProgress(CreditosConsumer.creditos.getEmprendimiento()[0]);
                progresoInformatica.setMax(CreditosConsumer.creditos.getCompuclub()[1]);
                progresoInformatica.setProgress(CreditosConsumer.creditos.getCompuclub()[0]);
                progresoInvestigacion.setMax(CreditosConsumer.creditos.getInvestigacion()[1]);
                progresoInvestigacion.setProgress(CreditosConsumer.creditos.getInvestigacion()[0]);
                progresoBienestar.setMax(CreditosConsumer.creditos.getBienestar()[1]);
                progresoBienestar.setProgress(CreditosConsumer.creditos.getBienestar()[0]);
            }
        }, 2000);

        return root;
    }
}




















