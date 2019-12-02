package com.example.myapplication.ui.about;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AboutViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public AboutViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Тут сведения обо мне. Какой я класный программист =)");
    }

    public LiveData<String> getText() {
        return mText;
    }
}