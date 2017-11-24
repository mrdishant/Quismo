package com.nearur.quismo;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Collections;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private BroadcastReceiver receiver=new Checking();

    PDetails pDetails;
    Money money;
    Motivation motivation;
    Days days;
    FragmentManager fragmentManager;
    FrameLayout frameLayout;
    CardView moneycard,dayscard,motivationcard,detailscard;

    void init(){

        ArrayList<Integer> colors = new ArrayList<Integer>();

        for (int c : ColorTemplate.VORDIPLOM_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.JOYFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.COLORFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.LIBERTY_COLORS)
            colors.add(c);

        /*for (int c : ColorTemplate.PASTEL_COLORS)
            colors.add(c);
*/
        colors.add(ColorTemplate.getHoloBlue());
        Collections.shuffle(colors);
        int c=0;


        pDetails=new PDetails();
        money=new Money();
        frameLayout=(FrameLayout)findViewById(R.id.jarvis4);
        frameLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,Jarvis.class);
                startActivity(intent);
            }
        });
        motivation=new Motivation();
        days=new Days();
        fragmentManager=getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.days,days).commit();
        fragmentManager.beginTransaction().add(R.id.money,money).commit();
        fragmentManager.beginTransaction().add(R.id.motivation,motivation).commit();
        fragmentManager.beginTransaction().add(R.id.details,pDetails).commit();

        moneycard=(CardView)findViewById(R.id.money);

        moneycard.setCardBackgroundColor(colors.get(c++));
        moneycard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MainActivity.this,MoneyChart.class);
                startActivity(i);
            }
        });

        detailscard=(CardView)findViewById(R.id.details);
        detailscard.setCardBackgroundColor(colors.get(c++));


        motivationcard=(CardView)findViewById(R.id.motivation);
        motivationcard.setCardBackgroundColor(colors.get(c++));
        motivationcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MainActivity.this,HelpUser.class);
                startActivity(i);
            }
        });

        dayscard=(CardView)findViewById(R.id.days);
        dayscard.setCardBackgroundColor(colors.get(c++));
        dayscard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MainActivity.this,DaysChart.class);
                startActivity(i);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Dashboard");
        toolbar.setBackgroundColor(Color.TRANSPARENT);

        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setBackgroundTintList(null);
        fab.setBackgroundColor(Color.TRANSPARENT);
        fab.setBackgroundTintMode(null);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MainActivity.this,Jarvis.class);
                startActivity(i);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);

        IntentFilter filter = new IntentFilter();
        filter.addAction("quismointent");
        registerReceiver(receiver, filter);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent i=new Intent("quismointent");
            sendBroadcast(i);
        }else if(id==R.id.home2){
            Intent intent=new Intent(this,MainActivity.class);
            startActivity(intent);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Intent intent=null;
        if (id == R.id.nav_camera) {
            intent=new Intent(this,MainActivity.class);
            startActivity(intent);
            finish();
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
            return true;
        }
        else if (id == R.id.nav_gallery) {
            intent=new Intent(this,MoneyChart.class);
        } else if (id == R.id.nav_slideshow) {
            intent=new Intent(this,DaysChart.class);
        } else if (id == R.id.nav_manage) {
            intent=new Intent(this,HelpUser.class);
        } else if (id == R.id.nav_share) {
            new SweetAlertDialog(this,SweetAlertDialog.CUSTOM_IMAGE_TYPE).setCustomImage(R.drawable.about)
                    .setTitleText("Developer").setContentText("Dishant Mahajan\nD3CSEA1\nRoll:1507567\nPhone:9023074222").show();
        }else if (id == R.id.chat) {
            intent=new Intent(this,Jarvis.class);
        }
        if(intent!=null){
        startActivity(intent);}
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }
}
