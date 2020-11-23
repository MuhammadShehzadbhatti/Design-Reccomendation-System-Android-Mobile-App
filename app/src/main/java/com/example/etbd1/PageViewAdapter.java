package com.example.etbd1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public  class PageViewAdapter extends FragmentStatePagerAdapter {
    public PageViewAdapter(FragmentManager fragmentManager){
        super(fragmentManager);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment= null;
        switch (position){

            case 0:
                fragment = new ShopProductsFragment();
                return fragment;

            case 1:
                fragment = new ShopDetailsFragment();
                return fragment;

        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 2;
    }
    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title = null;
        if (position==0){
            title="Home";
        }
        if (position==1){
            title="Details";
        }
        return title;
    }
}
