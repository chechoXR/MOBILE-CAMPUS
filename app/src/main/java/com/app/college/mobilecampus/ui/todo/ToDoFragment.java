package com.app.college.mobilecampus.ui.todo;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.college.mobilecampus.MainActivity;
import com.app.college.mobilecampus.R;
import com.app.college.mobilecampus.todo.tododatabase.TodoDbHelper;
import com.app.college.mobilecampus.ui.semilleros.SemillerosViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

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

        nav.setOnNavigationItemReselectedListener(listener);

        getChildFragmentManager().beginTransaction().replace(R.id.ToDoContainer,new ToDoHomeFragment()).commit();
        fabMove = nav.getItemIconSize()*1.8f;
        MainActivity.fab.setTranslationY(-fabMove);

        return root;

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        BottomNavigationView nav = getView().findViewById(R.id.todo_bottom_nav_bar);
        MainActivity.fab.setTranslationY(0);

    }


    @Override
    public void onPause() {
        super.onPause();


    }

    private BottomNavigationView.OnNavigationItemReselectedListener listener =
            new BottomNavigationView.OnNavigationItemReselectedListener() {
                @Override
                public void onNavigationItemReselected(@NonNull MenuItem menuItem) {

                    System.out.println("Item " + menuItem.getItemId()+" "+ menuItem.getTitle());
                    System.out.println("home: " + R.id.todo_Home);
                    System.out.println("");
                    System.out.println();
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
            }
    };


}