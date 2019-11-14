package com.app.college.mobilecampus.ui.todo;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.college.mobilecampus.R;

public class MateriaViewHolder  extends RecyclerView.ViewHolder {
    public TextView nombreMateria;
    public TextView correoProfesor;
    public MateriaViewHolder(@NonNull View itemView) {
        super(itemView);
        this.nombreMateria = itemView.findViewById(R.id.tv_nombre_materia);
        this.correoProfesor = itemView.findViewById(R.id.tv_correo_profesor);
    }
}
