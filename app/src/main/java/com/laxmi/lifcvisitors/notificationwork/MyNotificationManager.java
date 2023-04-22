package com.laxmi.lifcvisitors.notificationwork;


import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.AudioAttributes;
import android.net.Uri;
import android.os.Build;
import android.os.Vibrator;
import android.text.Html;

import androidx.core.app.NotificationCompat;


import com.laxmi.lifcvisitors.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


class MyNotificationManager {

    private static final int ID_BIG_NOTIFICATION = 234;
    private static final int ID_SMALL_NOTIFICATION = 235;
    private static final String ID_SPECIAL_OFFER_NOTIFICATION = "Test";

    private Context mCtx;

    MyNotificationManager(Context mCtx) {
        this.mCtx = mCtx;
    }

    void showBigNotification(String title, String message, String url, Intent intent) {

        String CHANNEL_ID = "Test";
        String CHANNEL_NAME = "Notification";

        // I removed one of the semi-colons in the next line of code
        NotificationManager notificationManager = (NotificationManager) mCtx.getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();
        inboxStyle.addLine(message);

        PendingIntent resultPendingIntent = PendingIntent.getActivity(mCtx, ID_BIG_NOTIFICATION, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // I would suggest that you use IMPORTANCE_DEFAULT instead of IMPORTANCE_HIGH
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH);
            channel.enableVibration(true);
            channel.setLightColor(Color.BLUE);
            channel.enableLights(true);

            channel.setSound(Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://" + mCtx.getPackageName() + "/" + R.raw.notification),
                    new AudioAttributes.Builder()
                            .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                            .setUsage(AudioAttributes.USAGE_NOTIFICATION)
                            .build());
            //channel.canShowBadge();
            // Did you mean to set the property to enable Show Badge?
            channel.setShowBadge(true);
            assert notificationManager != null;
            notificationManager.createNotificationChannel(channel);
        }

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(mCtx, CHANNEL_ID)
                .setVibrate(new long[]{0, 100})
                .setPriority(Notification.PRIORITY_MAX)
                .setLights(Color.BLUE, 3000, 3000)
                .setAutoCancel(true)
                .setContentTitle(title)
                .setContentIntent(resultPendingIntent)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setStyle(inboxStyle)
                .setLargeIcon(BitmapFactory.decodeResource(mCtx.getResources(), R.drawable.ic_alert_red))
                .setContentText(message);

        NotificationCompat.BigPictureStyle bigPictureStyle = new NotificationCompat.BigPictureStyle();
        bigPictureStyle.setBigContentTitle(title);
        bigPictureStyle.setSummaryText(Html.fromHtml(message).toString());
        bigPictureStyle.bigPicture(getBitmapFromURL(url));
        notificationBuilder.setSmallIcon(R.mipmap.ic_launcher).setTicker(title).setWhen(0)
                .setAutoCancel(true)
                .setContentIntent(resultPendingIntent)
                .setStyle(bigPictureStyle)
                .setChannelId("Test")
                .setContentText(message);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationBuilder.setChannelId(ID_SPECIAL_OFFER_NOTIFICATION);
        }

        assert notificationManager != null;
        notificationManager.notify(CHANNEL_ID, 1, notificationBuilder.build());

        //  notification.flags |= Notification.FLAG_AUTO_CANCEL;

        //notificationManager.notify(ID_BIG_NOTIFICATION, notification);

    }

    /*void showSmallNotification(String title, String message, Intent intent) {
        String CHANNEL_ID = "Test";
        String CHANNEL_NAME = "Notification";

        // I removed one of the semi-colons in the next line of code
        NotificationManager notificationManager = (NotificationManager) mCtx.getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();

        inboxStyle.addLine(message);

        PendingIntent resultPendingIntent =
                PendingIntent.getActivity(
                        mCtx,
                        ID_SMALL_NOTIFICATION,
                        intent,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // I would suggest that you use IMPORTANCE_DEFAULT instead of IMPORTANCE_HIGH
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH);
            channel.enableVibration(true);
            channel.setLightColor(Color.BLUE);
            channel.enableLights(true);
            channel.setSound(Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://" + mCtx.getPackageName() + "/" + R.raw.notification),
                    new AudioAttributes.Builder()
                            .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                            .setUsage(AudioAttributes.USAGE_NOTIFICATION)
                            .setFlags(Notification.FLAG_INSISTENT)
                            .build());
            //channel.canShowBadge();
            // Did you mean to set the property to enable Show Badge?
            channel.setShowBadge(true);
            assert notificationManager != null;
            notificationManager.createNotificationChannel(channel);
        }

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(mCtx, CHANNEL_ID)
                .setVibrate(new long[]{0, 100})
                .setPriority(Notification.PRIORITY_MAX)
                .setLights(Color.BLUE, 3000, 3000)
                .setAutoCancel(true)
                .setContentTitle(title)
                .setContentIntent(resultPendingIntent)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setStyle(inboxStyle)
                .setLargeIcon(BitmapFactory.decodeResource(mCtx.getResources(), R.drawable.ic_alert_red))
                .setContentText(message);
        // Removed .build() since you use it below...no need to build it twice

        // Don't forget to set the ChannelID!!
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationBuilder.setChannelId(ID_SPECIAL_OFFER_NOTIFICATION);
        }

        assert notificationManager != null;
        notificationManager.notify(CHANNEL_ID, 1, notificationBuilder.build());
    }*/
    void showSmallNotification(String title, String message, Intent intent) {
        String CHANNEL_ID = "Test";
        String CHANNEL_NAME = "Notification";
        // I removed one of the semi-colons in the next line of code
        NotificationManager notificationManager = (NotificationManager) mCtx.getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();
        inboxStyle.addLine(message);
        PendingIntent resultPendingIntent =
                null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            resultPendingIntent = PendingIntent.getActivity(
                    mCtx,
                    ID_SMALL_NOTIFICATION,
                    intent,
                    PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_MUTABLE
            );
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // I would suggest that you use IMPORTANCE_DEFAULT instead of IMPORTANCE_HIGH
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);

            AudioAttributes attributes = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_NOTIFICATION)
                    .build();

//            NotificationChannel channel = new NotificationChannel(CHANNEL_ID,
//                    mCtx.getString(R.string.app_name),
//                    NotificationManager.IMPORTANCE_HIGH);

            channel.enableVibration(true);
            channel.setLightColor(Color.BLUE);
            channel.enableLights(true);

            channel.setSound(Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://" + mCtx.getPackageName() + "/" + R.raw.notification),
                    new AudioAttributes.Builder()
                            .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                            .setUsage(AudioAttributes.USAGE_NOTIFICATION)
                            .build());

            //channel.canShowBadge();
            // Did you mean to set the property to enable Show Badge?
            channel.setShowBadge(true);
            assert notificationManager != null;
            notificationManager.createNotificationChannel(channel);
        }

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(mCtx, CHANNEL_ID)
        //Vibration
                .setVibrate(new long[] { 1000, 1000, 1000, 1000, 1000 })
                .setPriority(Notification.PRIORITY_MAX)
                .setLights(Color.BLUE, 3000, 3000)
                .setAutoCancel(true)
                .setContentTitle(title)
                .setContentIntent(resultPendingIntent)
                .setSmallIcon(R.drawable.life_logo)
                .setStyle(inboxStyle)
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText(message))
                .setContentText(message);

        Vibrator v = (Vibrator) this.mCtx.getSystemService(Context.VIBRATOR_SERVICE);
        // Vibrate for 500 milliseconds
        v.vibrate(500);

        // Removed .build() since you use it below...no need to build it twice

        // Don't forget to set the ChannelID!!
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationBuilder.setChannelId(ID_SPECIAL_OFFER_NOTIFICATION);

        }

        assert notificationManager != null;
        notificationManager.notify(CHANNEL_ID, 1, notificationBuilder.build());
    }

    //The method will return Bitmap from an image URL
    private Bitmap getBitmapFromURL(String strURL) {
        try {
            URL url = new URL(strURL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}