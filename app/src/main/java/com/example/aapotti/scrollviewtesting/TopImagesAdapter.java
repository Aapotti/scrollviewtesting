package com.example.aapotti.scrollviewtesting;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Aapotti on 1.4.2019.
 */

public class TopImagesAdapter extends FragmentPagerAdapter
{
    public TopImagesAdapter(FragmentManager fm)
    {
        super(fm);
    }

    @Override
    public Fragment getItem(int position)
    {
        TopImagesFragment topImagesFragment = new TopImagesFragment();
        Bundle bundle = new Bundle();
        position = position + 1;
        bundle.putInt("pageNumber", position);
        topImagesFragment.setArguments(bundle);


        return topImagesFragment;
    }

    @Override
    public int getCount()
    {
        return 5;
    }
}
