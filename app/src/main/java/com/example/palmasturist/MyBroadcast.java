package com.example.palmasturist;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class MyBroadcast extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String CHANNEL_ID = "2";
        int NOT_ID = 1;

        Intent i = new Intent(context,MainActivity.class);
        PendingIntent pi = PendingIntent.getActivity(context,0, i,0);
        Notification builder =  new NotificationCompat
                .Builder(context,CHANNEL_ID)
                .setSmallIcon(R.drawable.logo)
                .setContentTitle("Palmas Turist ")
                .setContentText("Onde voce quer ir hoje ?")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pi)
                .setAutoCancel(true)
                .build();

        NotificationManagerCompat nm = NotificationManagerCompat.from(context);
        nm.notify(NOT_ID,builder);
    }

}
