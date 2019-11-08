package com.app.college.mobilecampus.ui.todo;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.app.college.mobilecampus.R;

public class TareaViewHolder extends RecyclerView.ViewHolder {
    public TextView nombre;
    public TextView fecha;
    public TareaViewHolder(View view){
        super(view);
        this.nombre = (TextView) itemView.findViewById(R.id.NombreMat);
        this.fecha = (TextView) itemView.findViewById(R.id.fechaEnd);
    }
}
