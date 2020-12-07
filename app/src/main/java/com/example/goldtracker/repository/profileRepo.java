package com.example.goldtracker.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.goldtracker.dao.ProfileDao;
import com.example.goldtracker.database.profileDatabase;
import com.example.goldtracker.models.Profile;

public class profileRepo {
    private ProfileDao profileDao;
    private LiveData<Profile> profileLiveData;

    public profileRepo(Application application){
        profileDatabase db = profileDatabase.getDatabase(application);
        profileDao = db.profileDao();
        profileLiveData = profileDao.getProfile();
    }

    public LiveData<Profile> getProfileLiveData(){
        return profileLiveData;
    }

    public void insert(Profile profile){
        new insertAsyncTask(profileDao).execute(profile);
    }



    private class insertAsyncTask extends AsyncTask<Profile,Void,Void> {

        private ProfileDao profileDao;

        public insertAsyncTask(ProfileDao dao) {
            profileDao = dao;
        }

        @Override
        protected Void doInBackground(Profile... profiles) {
            profileDao.insert(profiles[0]);
            return null;
        }
    }

    public void deleteAll(){
        new deleteAllAsyncTask(profileDao).execute();
    }

    private class deleteAllAsyncTask extends AsyncTask<Void,Void,Void>{

        private ProfileDao profileDao;

        public deleteAllAsyncTask(ProfileDao dao) {
            profileDao = dao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            profileDao.deleteAll();
            return null;
        }
    }

    public void update(Profile profile){
        new updateAsyncTask(profileDao).execute(profile);
    }

    private class updateAsyncTask extends AsyncTask<Profile,Void,Void>{
        private ProfileDao profileDao;

        public updateAsyncTask(ProfileDao profileDao) {
            this.profileDao = profileDao;
        }

        @Override
        protected Void doInBackground(Profile... profiles) {
            profileDao.update(profiles[0]);
            return null;
        }
    }
}
