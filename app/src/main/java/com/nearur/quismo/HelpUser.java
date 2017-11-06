package com.nearur.quismo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class HelpUser extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_user);


    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
