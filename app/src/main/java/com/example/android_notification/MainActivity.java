package com.example.android_notification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity<Faisal> extends AppCompatActivity {

    private static String Faisal;
    private static final String CHANNEL_ID = "Faisal";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Create an explicit intent for an Activity in your app
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE);

        setContentView(R.layout.activity_main);
        createNotificationChannel();
    }

    NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_baseline_headphones_24)
            .setContentTitle("Headphone Warning")
            .setContentText("You're Using Your HeadPhones For Long Time")
            .setStyle(new NotificationCompat.BigTextStyle()
                    .bigText("You're Using Your HeadPhones For Long Time ..."))
            .setPriority(NotificationCompat.PRIORITY_MAX);

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.music);
            String description = getString(R.string.you_can_hear_million_song);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
         // notificationId is a unique int for each notification that you must define
            notificationManager.notify(100, builder.build());
    }
}