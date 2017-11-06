package com.nearur.quismo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class Splash extends AppCompatActivity {

    TextView title;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        sharedPreferences=getSharedPreferences("Quismo",MODE_PRIVATE);
        title=(TextView)findViewById(R.id.titlesplash);
        h.sendEmptyMessageDelayed(102,0);
        if(sharedPreferences.contains("Name")){
            h.sendEmptyMessageDelayed(103,2000);
        }
        else{
            h.sendEmptyMessageDelayed(101,2000);
        }

    }
    Handler h=new Handler(){
        @Override
        public void handleMessage(Message msg) {

            if(msg.what==103){
                Intent i=new Intent(Splash.this,MainActivity.class);
                startActivity(i);
                finish();
            }
            if(msg.what==102){
                Animation animation= AnimationUtils.loadAnimation(Splash.this,R.anim.splash);
                title.setVisibility(View.VISIBLE);
                title.startAnimation(animation);
            }
            if(msg.what==101){
                Intent i=new Intent(Splash.this,Details.class);
                startActivity(i);
                finish();
            }
        }
    };
}
