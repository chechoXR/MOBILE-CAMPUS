package com.app.college.mobilecampus.ui.todo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.college.mobilecampus.R;
import com.app.college.mobilecampus.todo.Tarea;

import java.util.ArrayList;

public class TareaAdapter extends RecyclerView.Adapter <TareaViewHolder> {
    private ArrayList<Tarea> listaTareas;
    public TareaAdapter(ArrayList<Tarea> listaTareas){
        this.listaTareas=listaTareas;
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
        tareaViewHolder.fecha.setText(tmptarea.getFecha_fin());
    }
    @Override
    public int getItemCount() {
        return listaTareas.size();
    }
}
