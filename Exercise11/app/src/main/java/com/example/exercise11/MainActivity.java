package com.example.exercise11;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private static final String CHANNEL_ID = "my_channel_01";
    private static final int NOTIFICATION_ID = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button b1 = findViewById(R.id.button);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNotification();
            }
        });
    }

    private void addNotification() {
        // Create a Notification Manager
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        // Create a Notification Channel (for Android 8.0 and above)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(
                    CHANNEL_ID,
                    "Android Notification",
                    NotificationManager.IMPORTANCE_DEFAULT
            );
            manager.createNotificationChannel(notificationChannel);
        }

        // Set contents for Notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.download)
                .setContentTitle("Notification Alert, Click Me")
                .setContentText("Hi, This is Android Notification Detail!")
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText("Hi, This is Android Notification Detail!"))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        // Attach Actions
        Intent notificationIntent = new Intent(this, NotificationView.class);
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent contentIntent = PendingIntent.getActivity(
                this,
                0,
                notificationIntent,
                PendingIntent.FLAG_IMMUTABLE // Use FLAG_IMMUTABLE for compatibility
        );
        builder.setContentIntent(contentIntent);


        // Deliver the notification manager.notify(NOTIFICATION_ID, builder.build());
    }
}
