package edu.temple.androidlab7;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import static edu.temple.androidlab7.R.id.webView;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link WebFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WebFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "Position";
    private static final String URL_KEY = "urlKey";

    // TODO: Rename and change types of parameters
    private int position;
    private String url;

    private boolean stopOnCreateViewFromBeingCalled = false;

    public WebFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param position Parameter 1.
     * @return A new instance of fragment WebFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static WebFragment newInstance(int position, String url) {
        WebFragment fragment = new WebFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, position);
        args.putString(URL_KEY, url);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            position = getArguments().getInt(ARG_PARAM1);
            url = getArguments().getString(URL_KEY);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_web, container, false);
        Log.d("OnCreateView", "OnCreateView is being called");
        WebView wv = (WebView) v.findViewById(webView);
        wv.getSettings().setJavaScriptEnabled(true);
        wv.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return true;
            }
        });

        if (url != null) {
            url = getArguments().getString(URL_KEY);
            Log.d("OnCreateView", url);
            wv.loadUrl(url);
        } else {
            wv.loadUrl("http://www.bing.com");
        }

        return v;
    }

}
