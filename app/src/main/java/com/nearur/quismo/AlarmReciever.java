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
       if(intent.getAction().equals("a.b.c.d")||intent.getAction().equals("android.intent.action.BOOT_COMPLETED")){
           Intent i=new Intent(context,NotificationService.class);
           PendingIntent pendingIntent=PendingIntent.getService(context,101,i,0);

           AlarmManager alarmManager=(AlarmManager)context.getSystemService(ALARM_SERVICE);
           Calendar c=Calendar.getInstance();
           c.set(Calendar.HOUR_OF_DAY,20);
           c.set(Calendar.MINUTE,00);
           c.set(Calendar.SECOND,00);
           Toast.makeText(context,"Alram Set",Toast.LENGTH_LONG).show();
           alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP,c.getTimeInMillis(),AlarmManager.INTERVAL_DAY,pendingIntent);
       }
       if (intent.getAction().equals("a.b.c")){
           Intent i=new Intent(context,NotificationService.class);
           PendingIntent pendingIntent=PendingIntent.getService(context,1001,i,0);
           Toast.makeText(context,"Intent Fired",Toast.LENGTH_LONG).show();
           AlarmManager alarmManager=(AlarmManager)context.getSystemService(ALARM_SERVICE);
           Calendar c=Calendar.getInstance();
           alarmManager.set(AlarmManager.RTC_WAKEUP,c.getTimeInMillis()+30000,pendingIntent);
       }
    }
}
