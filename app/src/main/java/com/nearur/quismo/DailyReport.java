package com.nearur.quismo;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.text.SimpleDateFormat;
import java.util.Date;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class DailyReport extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    ContentResolver contentResolver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_report);

        sharedPreferences=getSharedPreferences("Quismo",MODE_PRIVATE);
        editor=sharedPreferences.edit();
        contentResolver=getContentResolver();

        new SweetAlertDialog(DailyReport.this,SweetAlertDialog.WARNING_TYPE).setTitleText("Today")
                .setContentText("Do You Smoke Today?")
                .setConfirmText("Yes")
                .setCancelText("No").setCustomImage(R.drawable.daily)
                .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        editor.putInt("Days",(sharedPreferences.getInt("Days",1)+1));
                        editor.putInt("Dayssmoke",(sharedPreferences.getInt("Dayssmoke",1)+1));
                        Date d=new Date();
                        SimpleDateFormat dateFormat=new SimpleDateFormat("dd/MM/yyyy");
                        ContentValues values=new ContentValues();
                        values.put(Util.date,dateFormat.format(d).toString());
                        values.put(Util.saved,sharedPreferences.getInt("Daily",0));
                        values.put(Util.wasted,0);
                        values.put(Util.number,0);

                        contentResolver.insert(Util.u,values);
                        editor.commit();

                        Intent intent=new Intent(DailyReport.this,MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                })
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        editor.putInt("Dayssmoke",0);
                        editor.commit();
                        Intent intent=new Intent(DailyReport.this,Consumption.class);
                        startActivity(intent);
                        finish();
                    }
                })
                .show();


       /* AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setMessage("Do you Smoke Today ?");
        builder.setTitle("Today");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                editor.putInt("Dayssmoke",0);
                editor.commit();
                Intent intent=new Intent(DailyReport.this,Consumption.class);
                startActivity(intent);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                     editor.putInt("Days",(sharedPreferences.getInt("Days",1)+1));
                     editor.putInt("Dayssmoke",(sharedPreferences.getInt("Dayssmoke",1)+1));
                Date d=new Date();
                SimpleDateFormat dateFormat=new SimpleDateFormat("dd/MM/yyyy");
                ContentValues values=new ContentValues();
                values.put(Util.date,dateFormat.format(d).toString());
                values.put(Util.saved,sharedPreferences.getInt("Daily",0));
                values.put(Util.wasted,0);
                values.put(Util.number,0);

                contentResolver.insert(Util.u,values);
                     editor.commit();

                Intent intent=new Intent(DailyReport.this,MainActivity.class);
                startActivity(intent);
            }
        });

        AlertDialog dialog=builder.create();
        dialog.show();
        dialog.setCancelable(false);
*/
    }
}
