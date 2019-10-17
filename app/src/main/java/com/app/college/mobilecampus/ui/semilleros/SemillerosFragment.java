package com.app.college.mobilecampus.ui.semilleros;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.college.mobilecampus.R;

public class SemillerosFragment extends Fragment {

    private SemillerosViewModel mViewModel;

    public static SemillerosFragment newInstance() {
        return new SemillerosFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.semilleros_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(SemillerosViewModel.class);
        // TODO: Use the ViewModel
    }

}
