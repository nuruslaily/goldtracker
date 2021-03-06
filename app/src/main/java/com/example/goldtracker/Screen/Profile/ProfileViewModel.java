package com.example.goldtracker.Screen.Profile;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.goldtracker.models.Profile;
import com.example.goldtracker.repository.profileRepo;

public class ProfileViewModel extends ViewModel {
    private profileRepo profileRepository;
    private MutableLiveData<Profile> profileMutableLiveData = new MutableLiveData<>();

    public ProfileViewModel(Application application){
        super();
        profileRepository = new profileRepo(application);
    }

    public void onSaveProfile(String username,String email){
        Profile profile = new Profile(username,email,"");
        profileRepository.deleteAll();
        profileRepository.insert(profile);
        profileMutableLiveData.setValue(profile);
    }

    public LiveData<Profile> getProfileLiveData(){
        return profileMutableLiveData;
    }

    public void onSaveProfileNavigated(){
        profileMutableLiveData.setValue(null);
    }

}
