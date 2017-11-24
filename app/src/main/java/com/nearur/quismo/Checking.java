package com.nearur.quismo;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import java.util.Calendar;

import static android.content.Context.ALARM_SERVICE;

public class Checking extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("quismointent")){
            Intent i=new Intent(context,NotificationService.class);
            PendingIntent pendingIntent=PendingIntent.getService(context,1507623,i,0);
            Toast.makeText(context,"Intent Fired",Toast.LENGTH_LONG).show();
            AlarmManager alarmManager=(AlarmManager)context.getSystemService(ALARM_SERVICE);
            Calendar c=Calendar.getInstance();
            alarmManager.set(AlarmManager.RTC_WAKEUP,c.getTimeInMillis()+3000,pendingIntent);
        }
    }
}
