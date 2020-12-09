package com.example.goldtracker.Screen.Wallet;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.goldtracker.Screen.Profile.ProfileViewModel;

public class WalletViewModelFactory implements ViewModelProvider.Factory {
    private Application app;

    public WalletViewModelFactory(Application app) {
        this.app=app;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(ProfileViewModel.class)) {
            return (T) new ProfileViewModel(app);
        }
        throw new IllegalArgumentException("Wrong View Model Class");
    }
}
