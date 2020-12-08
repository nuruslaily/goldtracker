package com.example.goldtracker.Screen.Home;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.goldtracker.models.Profile;
import com.example.goldtracker.repository.profileRepo;

public class HomeViewModel extends ViewModel {
    private profileRepo profileRepository;
    private LiveData<Profile> profileLiveData;

    public HomeViewModel(Application home){
        super();
        profileRepository = new profileRepo(home);
        profileLiveData = profileRepository.getProfileLiveData();
    }

    public LiveData<Profile> getProfileLiveData(){
        return profileLiveData;
    }

    public void insert(Profile profile){
        profileRepository.insert(profile);
    }

    public void update(Profile profile){
        profileRepository.update(profile);
    }
}
