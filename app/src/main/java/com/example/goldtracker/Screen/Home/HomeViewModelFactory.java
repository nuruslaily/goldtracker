package com.example.goldtracker.Screen.Home;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.goldtracker.Screen.Profile.ProfileViewModel;

public class HomeViewModelFactory implements ViewModelProvider.Factory {
    private Application home;
    public HomeViewModelFactory(Application home) {
        this.home=home;
    }
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            return (T) new ProfileViewModel(home);
    }
}
