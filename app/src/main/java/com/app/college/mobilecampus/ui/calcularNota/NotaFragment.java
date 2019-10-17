package com.app.college.mobilecampus.ui.calcularNota;

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

public class NotaFragment extends Fragment {

    private NotaViewModel notaViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notaViewModel =
                ViewModelProviders.of(this).get(NotaViewModel.class);
        View root = inflater.inflate(R.layout.fragment_nota, container, false);

        return root;
    }
}