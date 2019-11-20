package com.app.college.mobilecampus.ui.requisitosGrado;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;



public class FragmentRequisitosdegradoViewModel extends ViewModel {

    private MutableLiveData<String> Mtext;

    public FragmentRequisitosdegradoViewModel (){
        Mtext = new MutableLiveData<>();
        Mtext.setValue("This is tools fragment");
    }

    public LiveData <String> getText () {return Mtext;}

}
