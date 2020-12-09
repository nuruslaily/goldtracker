package com.example.goldtracker.Screen.Wallet;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.goldtracker.models.Profile;
import com.example.goldtracker.models.Wallet;
import com.example.goldtracker.repository.profileRepo;

import java.util.List;

public class WalletViewModel extends ViewModel {
    private MutableLiveData<List<Wallet>> walletMutableLiveData = new MutableLiveData<List<Wallet>>();
    private List<Wallet> walletList = null;

    public WalletViewModel(List<Wallet> walletList) {
        this.walletList=walletList;
        this.walletMutableLiveData.setValue(walletList);
    }


    public LiveData<List<Wallet>> walletLiveData (){
        return walletMutableLiveData;
    }

    private MutableLiveData<Wallet> _navigateToListPlayer = new MutableLiveData<>();

    public LiveData<Wallet> navigateToListPlayer(){
        return _navigateToListPlayer;
    }
}
