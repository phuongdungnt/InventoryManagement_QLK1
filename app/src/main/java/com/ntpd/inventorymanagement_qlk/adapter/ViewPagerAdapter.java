package com.ntpd.inventorymanagement_qlk.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.ntpd.inventorymanagement_qlk.fragment.HomeFragment;
import com.ntpd.inventorymanagement_qlk.fragment.ProfileFragment;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new HomeFragment();

            case 1:
                return new ProfileFragment();

            default:
                return new HomeFragment();
        }

    }

    @Override
    public int getCount() {
        return 2;
    }
}
