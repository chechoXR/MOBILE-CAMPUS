package com.app.college.mobilecampus.ui.requisitosGrado;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.college.mobilecampus.R;

public class fragment_requisitosdegrado extends Fragment {

    private FragmentRequisitosdegradoViewModel mViewModel;

    public static fragment_requisitosdegrado newInstance() {
        return new fragment_requisitosdegrado();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_requisitosdegrado, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(FragmentRequisitosdegradoViewModel.class);
        // TODO: Use the ViewModel
    }

}
