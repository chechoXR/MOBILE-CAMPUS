package com.app.college.mobilecampus.ui.bienestar;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.app.college.mobilecampus.R;
import com.app.college.mobilecampus.model.Bienestar;

import java.util.ArrayList;

public class BienestarListView extends ArrayAdapter<Bienestar> {

    private Activity context;
    private ArrayList<Bienestar> cursos;


    public BienestarListView(Activity context, ArrayList<Bienestar> cursos) {
        super(context, R.layout.bienestar_list,cursos);
        this.context=context;
        this.cursos=cursos;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.bienestar_list, null, true);
        TextView nombre = (TextView) rowView.findViewById(R.id.nombre_bienestar);
        TextView modalidad = (TextView) rowView.findViewById(R.id.modalidad_bienestar);
        TextView descripcion = (TextView) rowView.findViewById(R.id.descripcion_bienestar);
        TextView correo = (TextView) rowView.findViewById(R.id.correo_bienestar);
        TextView tipo = (TextView) rowView.findViewById(R.id.tipo_bienestar);
        TextView profesor = (TextView) rowView.findViewById(R.id.profesor_bienestar);

        nombre.setText(cursos.get(position).getNombre());
        modalidad.setText(cursos.get(position).getModalidad());
        descripcion.setText("\n"+cursos.get(position).getDescripcion());
        correo.setText(cursos.get(position).getCorreo());
        tipo.setText(cursos.get(position).getTipo());
        profesor.setText(cursos.get(position).getProfesor());
        return rowView;
    }
}
