package com.nearur.quismo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Jarvis extends AppCompatActivity {
    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jarvis);
        getSupportActionBar().hide();
        webView=(WebView)findViewById(R.id.webJarvis);
        WebViewClient webViewClient=new WebViewClient();
        webView.setWebViewClient(webViewClient);
        webView.loadUrl("https://console.api.ai/api-client/demo/embedded/bce619fd-bd09-4a22-ae1d-c0454f32ad55");
        webView.getSettings().setJavaScriptEnabled(true);
    }
}
