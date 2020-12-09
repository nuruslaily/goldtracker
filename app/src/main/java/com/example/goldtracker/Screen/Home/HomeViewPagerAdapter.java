package com.example.goldtracker.Screen.Home;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.goldtracker.Screen.Buysell.BuysellFragment;
import com.example.goldtracker.Screen.Transaction.TransactionFragment;
import com.example.goldtracker.Screen.Wallet.WalletFragment;

import java.util.ArrayList;
import java.util.List;

public class HomeViewPagerAdapter extends FragmentStateAdapter {

    public HomeViewPagerAdapter(@NonNull Fragment fragment) {
        super(fragment);
    }
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new WalletFragment();
            case 1:
                return new TransactionFragment();
            case 2:
                return new BuysellFragment();
            default:
                return null;
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
