package com.app.college.mobilecampus.ui.todo;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;


import androidx.cardview.widget.CardView;

import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.app.college.mobilecampus.R;
import com.google.android.material.behavior.SwipeDismissBehavior;


public class TareaViewHolder extends RecyclerView.ViewHolder {
    public TextView nombre;
    public TextView fecha;
    private CardView textCardView;
    public TareaViewHolder(View view){
        super(view);
        this.nombre = (TextView) itemView.findViewById(R.id.NombreMat);
        this.fecha = (TextView) itemView.findViewById(R.id.fechaEnd);
        textCardView = (CardView) view.findViewById(R.id.Full_Card);
        implementSwipeDismiss();
    }

    private void implementSwipeDismiss() {
        SwipeDismissBehavior swipeDismissBehavior = new SwipeDismissBehavior();
        swipeDismissBehavior.setSwipeDirection(SwipeDismissBehavior.SWIPE_DIRECTION_START_TO_END);//Swipe direction i.e any direction, here you can put any direction LEFT or RIGHT

        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) textCardView.getLayoutParams();

        layoutParams.setBehavior(swipeDismissBehavior);//set swipe behaviour to Coordinator layout
        
    }
}
