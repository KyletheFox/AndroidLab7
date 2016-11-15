package edu.temple.androidlab7;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Iterator;

public class MainActivity extends AppCompatActivity {

    static final String HOME_PAGE = "http://www.bing.com";

    Button button;
    EditText editText;
    WebAdapter webAdapter;
    ViewPager viewPager;
    String currentUrl;
    int currentFrag, maxFrag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Setting up the toolbar on top of app
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.showOverflowMenu();

        currentFrag = 1;
        maxFrag = 2;
        currentUrl = HOME_PAGE;

        button = (Button) findViewById(R.id.urlButton);
        editText = (EditText) findViewById(R.id.urlEditText);
        viewPager = (ViewPager) findViewById(R.id.viewPager);

        webAdapter = new WebAdapter(getSupportFragmentManager());
        viewPager.setAdapter(webAdapter);
        webAdapter.addUrl(HOME_PAGE);
//        webAdapter.addUrl(HOME_PAGE);


        viewPager.setCurrentItem(maxFrag);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                webAdapter.addUrl(editText.getText().toString());
//                webAdapter.notifyDataSetChanged();
//                Log.d("Url", webAdapter.urls.toString());
//                Log.d("Url", String.valueOf(viewPager.getCurrentItem()));

                // Loads the new URL into webView
                currentUrl = editText.getText().toString();
                WebView webView = (WebView) findViewById(R.id.webView);
                webView.loadUrl(currentUrl);

                // Updates
                webAdapter.updateUrl(currentUrl, viewPager.getCurrentItem());
                webAdapter.notifyDataSetChanged();
                Log.d("URL List", webAdapter.urls.toString());

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_bar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_back:
                if (currentFrag > 1) {
                    currentFrag--;
                    viewPager.setCurrentItem(currentFrag);
                    Log.d("Fragment Number", "Opening Fragment: " + currentFrag);
                }
                return true;
            case R.id.action_forward:
                if (currentFrag < maxFrag) {
                    currentFrag++;
                    viewPager.setCurrentItem(currentFrag);
                    Log.d("Fragment Number", "Opening Fragment: " + currentFrag);
                }
                return true;
            case R.id.action_new:
                maxFrag++;
                currentFrag = maxFrag;
                webAdapter.addUrl(editText.getText().toString());
                webAdapter.notifyDataSetChanged();
                viewPager.setCurrentItem(currentFrag);

                Log.d("URL List", webAdapter.urls.toString());

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public class WebAdapter extends FragmentStatePagerAdapter {

        ArrayList urls = new ArrayList<>();
        Iterator iter = urls.iterator();


        public WebAdapter(FragmentManager fm) {
            super(fm);
        }

        public void addUrl(String str) {
            urls.add(str);
        }

        public void updateUrl(String str, int position) {
            urls.set(position, str);
        }

        @Override
        public void startUpdate(ViewGroup container) {
            super.startUpdate(container);
        }

        @Override
        public int getCount() {
            return maxFrag;
        }

        @Override
        public Fragment getItem(int position) {
            Log.d("getItem", String.valueOf(position));
            if (position >= 1)
                return WebFragment.newInstance(position, urls.get(position-1).toString());
            else
                return WebFragment.newInstance(position, "http://www.bing.com");

        }

    }




}
