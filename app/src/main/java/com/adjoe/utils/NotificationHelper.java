package com.adjoe.utils;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

import com.adjoe.R;

public class NotificationHelper {


    public static void addNotification(Context context , String title , String detail) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel notificationChannel = new NotificationChannel("ID", "Name", importance);
            notificationManager.createNotificationChannel(notificationChannel);
            builder = new NotificationCompat.Builder(context, notificationChannel.getId());
        } else {
            builder = new NotificationCompat.Builder(context);
        }

        builder = builder
                .setSmallIcon(R.drawable.ic_time)
                .setColor(ContextCompat.getColor(context, R.color.colorAccent))
                .setContentTitle(title)
                .setContentText(detail)
                .setDefaults(Notification.DEFAULT_ALL)
                .setAutoCancel(true);
        if (notificationManager != null) {
            notificationManager.notify(1, builder.build());
        }
    }

}
