package com.app.college.mobilecampus.ui.todo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.college.mobilecampus.R;
import com.app.college.mobilecampus.todo.Materia;

import java.util.ArrayList;

public class MateriaAdapter extends RecyclerView.Adapter<MateriaViewHolder> {
    private ArrayList<Materia> list_materia;

    public MateriaAdapter(ArrayList<Materia> list_materia){
        this.list_materia=list_materia;
    }

    @NonNull
    @Override
    public MateriaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_materia_layout, parent,false);
        MateriaViewHolder materiaViewHolder = new MateriaViewHolder(v);
        return materiaViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MateriaViewHolder materiaViewHolder, int position) {
        Materia materiatmp = list_materia.get(position);
        materiaViewHolder.nombreMateria.setText(materiatmp.getNombre());
        materiaViewHolder.correoProfesor.setText(materiatmp.getCorreo_profesor());

    }

    @Override
    public int getItemCount() {
        return list_materia.size();
    }
}
