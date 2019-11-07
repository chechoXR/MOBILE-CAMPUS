package com.app.college.mobilecampus.ui.todo;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
import com.app.college.mobilecampus.MainActivity;
import com.app.college.mobilecampus.R;
import com.app.college.mobilecampus.todo.tododatabase.TodoDbHelper;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ToDoFragment extends Fragment {


    private ToDoViewModel toDoViewModel;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    public static float fabMove;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        toDoViewModel =
                ViewModelProviders.of(this).get(ToDoViewModel.class);
        View root = inflater.inflate(R.layout.fragment_todo, container, false);
        //Ejemplo para ver existencia de la base de datos

        TodoDbHelper todo = new TodoDbHelper(root.getContext());
        Log.i("Database name" , todo.getDatabaseName().trim().toString());




        BottomNavigationView nav = root.findViewById(R.id.todo_bottom_nav_bar);

        nav.setOnNavigationItemSelectedListener(listener);
        nav.setOnNavigationItemReselectedListener(reListener);

        //Nested fragment selected
        getChildFragmentManager().beginTransaction().replace(R.id.ToDoContainer,new ToDoHomeFragment()).commit();

        //Floating Button constant of movement in Y axis
        fabMove = nav.getItemIconSize()*1.8f;
        //Floating Button movement
        MainActivity.moveFloatingButton(-fabMove,System.currentTimeMillis());

        View v = inflater.inflate(R.layout.fragment_todo, container, false);
        setHasOptionsMenu(true);


        return root;

    }

    //Se añade el boton de agregar a la barra superior
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.todoadd, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        BottomNavigationView nav = getView().findViewById(R.id.todo_bottom_nav_bar);
        MainActivity.moveFloatingButton(0,System.currentTimeMillis());

    }


    @Override
    public void onPause() {
        super.onPause();


    }

    private BottomNavigationView.OnNavigationItemReselectedListener reListener =
            new BottomNavigationView.OnNavigationItemReselectedListener(){
                @Override
                public void onNavigationItemReselected(@NonNull MenuItem menuItem) {
                    //No se hace nada al reseleccionar un item de la barra de opciones inferior
                }
            };

    private BottomNavigationView.OnNavigationItemSelectedListener listener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                    Fragment selected = null;

                    switch (menuItem.getItemId()) {
                        case R.id.ToDoHome:
                            selected= new ToDoHomeFragment();
                            break;
                        case R.id.ToDoMateria:
                            selected = new ToDoMateriasFragment();
                            break;
                        case R.id.ToDoTareasMateria:
                            selected = new ToDoTareasMateriaFragment();
                            break;



                    }
                    getChildFragmentManager().beginTransaction().replace(R.id.ToDoContainer,selected).commit();
                    return true;
                }

    };


}