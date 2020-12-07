package com.example.goldtracker.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.goldtracker.dao.ProfileDao;
import com.example.goldtracker.models.Profile;

@Database(entities = {Profile.class},version = 1,exportSchema = false)
public abstract class profileDatabase extends RoomDatabase {
    public abstract ProfileDao profileDao();
    private static profileDatabase INSTANCE;

    public static profileDatabase getDatabase(final Context context){
        if(INSTANCE == null){
            synchronized (profileDatabase.class){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),profileDatabase.class,"gold_app_db")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
