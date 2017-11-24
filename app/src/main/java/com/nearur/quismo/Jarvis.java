package com.nearur.quismo;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class Jarvis extends AppCompatActivity {
    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jarvis);
        getSupportActionBar().hide();
        if(isConnected(getApplicationContext())){
        webView=(WebView)findViewById(R.id.webJarvis);
        WebViewClient webViewClient=new WebViewClient();
        webView.setWebViewClient(webViewClient);
        webView.loadUrl("https://console.api.ai/api-client/demo/embedded/bce619fd-bd09-4a22-ae1d-c0454f32ad55");
        webView.getSettings().setJavaScriptEnabled(true);}
        else {

            SweetAlertDialog s = new SweetAlertDialog(this,SweetAlertDialog.WARNING_TYPE);
            s.setTitleText("No Internet Connection").show();
            s.setContentText("You need to enable Moblie Data or Wi-Fi to progress further.");
            s.setConfirmText("WI-Fi").show();
            s.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                @Override
                public void onClick(SweetAlertDialog sweetAlertDialog) {
                    Intent intent = new Intent(Settings.ACTION_WIFI_SETTINGS);
                    startActivity(intent);
                    finish();
                }
            });
            s.setCancelText("Mobile Data").show();
            s.setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                @Override
                public void onClick(SweetAlertDialog sweetAlertDialog) {
                    Intent intent = new Intent(Settings.ACTION_DATA_ROAMING_SETTINGS);
                    startActivity(intent);
                    finish();
                }
            });
            s.show();
        }
    }

    public boolean isConnected(Context context) {

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netinfo = cm.getActiveNetworkInfo();

        if (netinfo != null && netinfo.isConnectedOrConnecting()) {
            android.net.NetworkInfo wifi = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            android.net.NetworkInfo mobile = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

            if((mobile != null && mobile.isConnectedOrConnecting()) || (wifi != null && wifi.isConnectedOrConnecting())) {
                return true;
            }
            else
                return false;
        } else
            return false;
    }
}
