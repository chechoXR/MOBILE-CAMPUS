package com.app.college.mobilecampus.ui.bienestar;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class BienestarViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public BienestarViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is slideshow fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}