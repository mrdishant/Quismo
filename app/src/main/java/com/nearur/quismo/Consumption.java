package com.nearur.quismo;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Consumption extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    ContentResolver contentResolver;
    Button submit;
    TextView textView;
    ImageButton plus,minus;
    int daily;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_consumption);

        sharedPreferences=getSharedPreferences("Quismo",MODE_PRIVATE);
        daily=sharedPreferences.getInt("Daily",0);


        contentResolver=getContentResolver();
        textView=(TextView)findViewById(R.id.textnumber);
        plus=(ImageButton)findViewById(R.id.add);
        minus=(ImageButton)findViewById(R.id.minus);
        submit=(Button)findViewById(R.id.submityes);


        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView.setText(Integer.parseInt(textView.getText().toString())+1+"");
            }
        });

        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView.setText(Integer.parseInt(textView.getText().toString())-1+"");
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int num=Integer.parseInt(textView.getText().toString());
                int price=sharedPreferences.getInt("Price",0);
                Date d=new Date();
                SimpleDateFormat dateFormat=new SimpleDateFormat("dd/MM/yyyy");
                ContentValues values=new ContentValues();
                values.put(Util.date,dateFormat.format(d).toString());
                values.put(Util.number,num);
                int wasted=price*num;
                values.put(Util.wasted,wasted);
                if(daily-wasted<=0){
                    values.put(Util.saved,0);
                }else{
                    values.put(Util.saved,daily-wasted);
                }
                contentResolver.insert(Util.u,values);
                Intent intent=new Intent(Consumption.this,HelpUser.class);
                startActivity(intent);
                finish();
            }
        });


    }
}
