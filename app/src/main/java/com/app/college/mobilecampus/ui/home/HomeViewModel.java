package com.app.college.mobilecampus.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.app.college.mobilecampus.model.Estudiante;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private  String name;


    public HomeViewModel() {
        name=Estudiante.class.getName();
        mText = new MutableLiveData<>();
        mText.setValue("Bienvennido"+ name);
    }



    public LiveData<String> getText() {
        return mText;
    }
}