package edu.temple.androidlab7;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

/**
 * Created by kyleoneill on 11/3/16.
 */

public class WebAdapter extends FragmentStatePagerAdapter {

    private String url;

    public WebAdapter(FragmentManager fm) {
        super(fm);
    }

    public void setUrl(String url) {
        this.url = url;
        Log.d("UrlAdapter", this.url);
    }

    @Override
    public int getCount() {
        return 10;   // Need to change
    }

    @Override
    public Fragment getItem(int position) {
        Log.d("GetItem", "Get item is being called");
        return WebFragment.newInstance(position, url);
    }


}


