package com.nearur.quismo;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import java.util.Calendar;

import static android.content.Context.ALARM_SERVICE;

public class AlarmReciever extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
       if(intent.getAction().equals("quismoeight")||intent.getAction().equals("android.intent.action.BOOT_COMPLETED")){
           Intent i=new Intent(context,NotificationService.class);
           PendingIntent pendingIntent=PendingIntent.getService(context,1507567,i,0);

           AlarmManager alarmManager=(AlarmManager)context.getSystemService(ALARM_SERVICE);
           Calendar c=Calendar.getInstance();
           c.set(Calendar.HOUR_OF_DAY,20);
           c.set(Calendar.MINUTE,00);
           c.set(Calendar.SECOND,00);
           Toast.makeText(context,"Alarm Set",Toast.LENGTH_LONG).show();
           alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP,c.getTimeInMillis(),AlarmManager.INTERVAL_DAY,pendingIntent);
       }

    }
}
