package com.app.college.mobilecampus.ui.todo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.app.college.mobilecampus.R;

public class ToDoFragment extends Fragment {

    private ToDoViewModel toDoViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        toDoViewModel =
                ViewModelProviders.of(this).get(ToDoViewModel.class);
        View root = inflater.inflate(R.layout.fragment_todo, container, false);

        return root;
    }
}