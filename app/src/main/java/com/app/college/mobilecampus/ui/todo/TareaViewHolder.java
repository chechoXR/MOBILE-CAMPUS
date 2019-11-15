package com.app.college.mobilecampus.ui.todo;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;


import androidx.cardview.widget.CardView;

import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.app.college.mobilecampus.R;
import com.google.android.material.behavior.SwipeDismissBehavior;

import org.w3c.dom.Text;


public class TareaViewHolder extends RecyclerView.ViewHolder {
    public TextView nombre;
    public TextView descripcion;
    public TextView fecha;
    public TextView materia;
    private CardView textCardView;
    public TareaViewHolder(View view){
        super(view);
        this.nombre = (TextView) itemView.findViewById(R.id.NombreMat);
        this.descripcion = (TextView) itemView.findViewById(R.id.tareaDesc);
        this.fecha = (TextView) itemView.findViewById(R.id.fechaEnd);
        this.materia = (TextView) itemView.findViewById(R.id.materiaDeTarea);
        textCardView = (CardView) view.findViewById(R.id.Full_Card);
        //implementSwipeDismiss();
    }

    private void implementSwipeDismiss() {
        SwipeDismissBehavior swipeDismissBehavior = new SwipeDismissBehavior();
        swipeDismissBehavior.setSwipeDirection(SwipeDismissBehavior.SWIPE_DIRECTION_START_TO_END);//Swipe direction i.e any direction, here you can put any direction LEFT or RIGHT


        swipeDismissBehavior.setListener(new SwipeDismissBehavior.OnDismissListener() {
            @Override
            public void onDismiss(View view) {
                System.out.println("Dismised!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!S");
                System.out.println("Dismised!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!S");
                System.out.println("Dismised!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!S");
                System.out.println("Dismised!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!S");



            }

            @Override
            public void onDragStateChanged(int i) {

            }
        });
        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) textCardView.getLayoutParams();

        layoutParams.setBehavior(swipeDismissBehavior);//set swipe behaviour to Coordinator layout


        
    }
}
