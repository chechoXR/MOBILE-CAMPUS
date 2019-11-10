package com.app.college.mobilecampus.ui.semilleros;

import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.app.college.mobilecampus.R;
import com.app.college.mobilecampus.model.Semillero;

import java.util.ArrayList;
import java.util.List;

public class SemilleroListVIew extends ArrayAdapter<Semillero> {


    private Activity context;
    private ArrayList<Semillero> semilleros;


    public SemilleroListVIew(Activity context, ArrayList<Semillero> semilleros) {
        super(context,R.layout.semillero_list,semilleros);
        this.context=context;
        this.semilleros=semilleros;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.semillero_list, null, true);
        TextView nombre = (TextView) rowView.findViewById(R.id.nombre);
        TextView descripcion = (TextView) rowView.findViewById(R.id.descripcion);
        TextView condicion = (TextView) rowView.findViewById(R.id.condicion);
        TextView profesor = (TextView) rowView.findViewById(R.id.profesor);
        TextView correo_profesor = (TextView) rowView.findViewById(R.id.correo_profesor);
        TextView estado = (TextView) rowView.findViewById(R.id.estado);

        nombre.setText(semilleros.get(position).getNombre());
        descripcion.setText(semilleros.get(position).getDescripcion());
        condicion.setText("Condición: \n"+semilleros.get(position).getCondicion());
        profesor.setText(semilleros.get(position).getProfesor());
        correo_profesor.setText(semilleros.get(position).getCorreo_profesor());
        correo_profesor.setTextColor(Color.parseColor("#0f385a"));
        estado.setText(semilleros.get(position).getEstado());
        estado.setTextColor(estado.getText().equals("activo")? Color.GREEN:Color.RED);
        return rowView;
    }
}
