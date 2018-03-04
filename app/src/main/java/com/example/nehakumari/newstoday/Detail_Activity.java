package com.example.nehakumari.newstoday;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

/**
 * Created by NEHA KUMARI on 01-03-2018.
 */

public class Detail_Activity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        WebView myWebView = (WebView) findViewById(R.id.webview);
        Bundle extras = getIntent().getExtras();
        if(extras!=null)
        {
            String newsUrl = extras.getString("url");
            myWebView.loadUrl(newsUrl);
        }


        // Enable Javascript
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        // Force links and redirects to open in the WebView instead of in a browser
        myWebView.setWebViewClient(new WebViewClient());

    }
}
