package com.nearur.quismo;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.support.v4.app.NotificationCompat;

public class NotificationService extends Service {

    NotificationManager notificationManager;

    @Override
    public int onStartCommand(Intent intent,int flags, int startId) {
        notificationManager=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder=new NotificationCompat.Builder(getApplicationContext());
        builder.setContentText("Daily Analysis ");
        builder.setContentTitle("Quismo");
        builder.setSmallIcon(R.drawable.a);
        builder.setDefaults(Notification.DEFAULT_ALL);

        Intent i=new Intent(getApplicationContext(),DailyReport.class);
        PendingIntent pending=PendingIntent.getActivity(getApplicationContext(),7623,i,PendingIntent.FLAG_UPDATE_CURRENT);

        builder.setContentIntent(pending);
        builder.setAutoCancel(true);
        Notification n=builder.build();
        notificationManager.notify(7623,n);
        return Service.START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
