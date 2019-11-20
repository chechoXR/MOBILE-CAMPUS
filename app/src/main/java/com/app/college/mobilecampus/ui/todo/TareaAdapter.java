package com.app.college.mobilecampus.ui.todo;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.college.mobilecampus.R;
import com.app.college.mobilecampus.todo.Materia;
import com.app.college.mobilecampus.todo.Tarea;

import java.util.ArrayList;

public class TareaAdapter extends RecyclerView.Adapter <TareaViewHolder> {
    private ArrayList<Tarea> listaTareas;
    private ArrayList<Materia> listaMaterias;

    public TareaAdapter(ArrayList<Tarea> listaTareas, ArrayList<Materia> listaMaterias){
        this.listaTareas=listaTareas;
        this.listaMaterias = listaMaterias;
    }

    @NonNull
    @Override
    public TareaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_tarea_layout, parent,false);
        TareaViewHolder tareaViewHolder = new TareaViewHolder(v);
        return tareaViewHolder;
  /*    Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View contactView =inflater.inflate(R.layout.custom_tarea_layout,parent,false);*/

    }

    @Override
    public void onBindViewHolder(@NonNull TareaViewHolder tareaViewHolder, int position) {
        Tarea tmptarea= listaTareas.get(position);
        tareaViewHolder.nombre.setText(tmptarea.getNombre());

        if(tmptarea.getCompletada() == 1)
            tareaViewHolder.nombre.setTextColor(Color.rgb(90,15,15));
        if(tmptarea.getCompletada() == 0)
            tareaViewHolder.nombre.setTextColor(Color.rgb(15,56,90));

        tareaViewHolder.descripcion.setText(tmptarea.getDescripcion());
        tareaViewHolder.fecha.setText(tmptarea.getFecha_fin());

        Materia m = listaMaterias.get(0);
        for (int i=0; i< listaMaterias.size();i++) {
            System.out.println(listaMaterias.get(i).getId());
            System.out.println(tmptarea.getId_materia());
            if (listaMaterias.get(i).getId() == tmptarea.getId_materia()) {
                m = listaMaterias.get(i);
                break;
            }
        }
        tareaViewHolder.materia.setText(m.getNombre());
    }
    @Override
    public int getItemCount() {
        return listaTareas.size();
    }

    public void update(){
        notifyDataSetChanged();
    }
}
