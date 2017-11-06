package com.nearur.quismo;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class DailyReport extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_report);

        sharedPreferences=getSharedPreferences("Quismo",MODE_PRIVATE);
        editor=sharedPreferences.edit();

        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setMessage("Do you Smoke Today ?");
        builder.setTitle("Today");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                editor.putInt("Dayssmoke",0);
                editor.commit();
                Intent intent=new Intent(DailyReport.this,HelpUser.class);
                startActivity(intent);
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                     editor.putInt("Days",(sharedPreferences.getInt("Days",1)+1));
                     editor.putInt("Dayssmoke",(sharedPreferences.getInt("Dayssmoke",1)+1));
                    editor.commit();
                Intent intent=new Intent(DailyReport.this,MainActivity.class);
                startActivity(intent);
            }
        });

        AlertDialog dialog=builder.create();
        dialog.show();
        dialog.setCancelable(false);

    }
}
