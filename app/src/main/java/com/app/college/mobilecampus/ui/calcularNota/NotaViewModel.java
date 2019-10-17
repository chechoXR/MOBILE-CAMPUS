package com.app.college.mobilecampus.ui.calcularNota;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class NotaViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public NotaViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is send fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}