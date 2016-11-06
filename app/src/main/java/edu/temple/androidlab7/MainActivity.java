package edu.temple.androidlab7;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button button;
    EditText editText;
    WebAdapter webAdapter;
    ViewPager viewPager;
    int currentFrag, maxFrag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.showOverflowMenu();

        currentFrag = 0;
        maxFrag = 0;

        button = (Button) findViewById(R.id.urlButton);
        editText = (EditText) findViewById(R.id.urlEditText);
        viewPager = (ViewPager) findViewById(R.id.viewPager);

        webAdapter = new WebAdapter(getSupportFragmentManager());
        viewPager.setAdapter(webAdapter);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            String url;
            url = editText.getText().toString();
            Log.d("Url", url);
            WebView webView = (WebView) findViewById(R.id.webView);
            webView.loadUrl(url);
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
                Toast.makeText(this, String.valueOf(currentFrag), Toast.LENGTH_SHORT).show();
                if (currentFrag > 0) {
                    currentFrag--;
                    viewPager.setCurrentItem(currentFrag);
                }
                return true;
            case R.id.action_forward:
                Toast.makeText(this, String.valueOf(currentFrag), Toast.LENGTH_SHORT).show();
                if (currentFrag < maxFrag) {
                    currentFrag++;
                    viewPager.setCurrentItem(currentFrag);
                }
                return true;
            case R.id.action_new:
                maxFrag++;
                currentFrag = maxFrag;
                viewPager.setCurrentItem(currentFrag);
                Toast.makeText(this, "Added new Fragment page", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}
