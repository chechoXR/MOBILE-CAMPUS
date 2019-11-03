package com.app.college.mobilecampus.ui.calcularNota;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import com.app.college.mobilecampus.R;


public class NotaFragment extends Fragment {

    private NotaViewModel notaViewModel;
    private EditText primerCorte;
    private EditText segundoCorte;
    private EditText tercerCorte;
    private TextView notaFinal;
    private Button botonCalcular;
    private Button botonClear;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        notaViewModel = ViewModelProviders.of(this).get(NotaViewModel.class);
        View root = inflater.inflate(R.layout.fragment_nota, container, false);

        primerCorte = root.findViewById(R.id.nota1);
        segundoCorte = root.findViewById(R.id.nota2);
        tercerCorte = root.findViewById(R.id.nota3);
        notaFinal = root.findViewById(R.id.TextView_notaFinal);
        botonCalcular = root.findViewById(R.id.Button_calcular);
        botonClear = root.findViewById(R.id.Button_clear);

        botonCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notaFinal.setText(calcular());
            }
        });

        botonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                primerCorte.setText("");
                segundoCorte.setText("");
                tercerCorte.setText("");
                notaFinal.setText("");
            }
        });
        return root;
    }

    private String calcular(){
        if(primerCorte.getText().toString().equals("") || segundoCorte.getText().toString().equals("")){
            Toast.makeText(this.getContext(), "Datos insuficientes", Toast.LENGTH_LONG).show();
            return"";
        }
        double nota1, nota2, nota3;
        try{
            nota1 = Double.parseDouble(primerCorte.getText().toString());
            nota2 = Double.parseDouble(segundoCorte.getText().toString());
            if(!tercerCorte.getText().toString().equals("")){
                nota3 = Double.parseDouble(tercerCorte.getText().toString());
                return String.format("%.2f", new Double((nota1 * 0.3) + (nota2 * 0.3) + (nota3 * 0.4)));
            }else{
                nota3 = (3 - (nota1 * 0.3) - (nota2 * 0.3)) / 0.4;
                tercerCorte.setText(String.format("%.2f", new Double(nota3)));
                return String.format("%.2f", new Double((nota1 * 0.3) + (nota2 * 0.3) + (nota3 * 0.4)));
            }
        }catch (NumberFormatException e){
            Toast.makeText(this.getContext(), "Datos no validos", Toast.LENGTH_LONG).show();
            return "";
        }
    }
}