package com.example.infiniterecharge.ui.notifications;

import android.app.Application;

import java.util.List;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class NotesViewModel extends AndroidViewModel {

    private MutableLiveData<String> mText;

    public NotesViewModel(Application application) {
        super(application);

        //mText.setValue("This is notifications fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }

}