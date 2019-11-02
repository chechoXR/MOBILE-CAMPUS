package com.app.college.mobilecampus.ui.semilleros;

import androidx.lifecycle.ViewModelProviders;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.college.mobilecampus.R;
import com.app.college.mobilecampus.ServicesConsumer.SemilleroConsumer;
import com.app.college.mobilecampus.model.Semillero;
import com.app.college.mobilecampus.utils.utils;

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
        final SemilleroConsumer semilleroConsumer = new SemilleroConsumer(this.getContext());
        semilleroConsumer.loadSemilleros();
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(SemillerosViewModel.class);


    }


}
