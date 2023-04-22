package com.laxmi.lifcvisitors.notificationwork;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.laxmi.lifcvisitors.activity.guard.GuardDashboard;
import com.laxmi.lifcvisitors.savedata.PrefConfig;

import org.json.JSONException;
import org.json.JSONObject;


public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = "MyFirebaseMsgService";

    private ProgressDialog pDialog;
    private PrefConfig prefConfig;

    public void onNewToken(@NonNull String token)
    {
        Log.d(TAG, "Refreshed token: " + token);
    }
    // Override onMessageReceived() method to extract the
    // title and
    // body from the message passed in FCM

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        if (remoteMessage.getData().size() > 0) {

            prefConfig = new PrefConfig(getApplicationContext());

            if (prefConfig.readLoginStatus())
                // Log.e(TAG, "Data Payload: " + remoteMessage.getData().toString());
                try {
                    JSONObject json = new JSONObject(remoteMessage.getData().toString());

                    //     Log.e(TAG, "Notification JSON " + json.toString());

                    //getting the json data
                    JSONObject data = json.getJSONObject("data");

                    //parsing json data
                    String message = data.getString("message");

                    //   String type = data.getString("type");
                    //  String type = data.getString("type");

                    //     Log.e(TAG, "Data Payload: " + message);

                    //    playNotificationSound(getApplicationContext());
//                if(type!=null){
//                    if(type.equalsIgnoreCase("global")){
//                        sendPushNotification(json);
//                    }
//                }

//                else {
                    if (message.equalsIgnoreCase("Lead Accepted By Other")) {
                        updateMyActivity(getApplicationContext(), message);
                    } else if (message.equalsIgnoreCase("You Get A New Lead")) {
                        sendPushNotification(json);
                        updateMyActivity(getApplicationContext(), message);
                    } else if (message.equalsIgnoreCase("You Get A Recomplaint Lead")) {
                        sendPushNotification(json);
                        updateMyActivity(getApplicationContext(), message);
                    } else {
                        sendPushNotification(json);
                    }
//                }


                } catch (Exception e) {
                    Log.e(TAG, "Exception: " + e.getMessage());
                }

            try {
                if (remoteMessage.getNotification() != null) {
                    Log.d(TAG, "Message Notification Body: " +
                            remoteMessage.getNotification().getBody());
                }

            } catch (Exception e) {
                Log.e(TAG, "Exception: " + e.getMessage());
            }
        }
    }

    private void sendPushNotification(JSONObject json) {
        //optionally we can display the json into log
        Log.e(TAG, "Notification JSON " + json.toString());
        try {
            //getting the json data
            JSONObject data = json.getJSONObject("data");

            //parsing json data
            String title = data.getString("title");
            String message = data.getString("message");
            String imageUrl = data.getString("image");

            //creating MyNotificationManager object
            MyNotificationManager mNotificationManager = new MyNotificationManager(getApplicationContext());

            //creating an intent for the notification
            Intent intent = new Intent(getApplicationContext(), GuardDashboard.class);

            //if there is no image
            if (imageUrl.equals("null")) {
                //displaying small notification
                mNotificationManager.showSmallNotification(title, message, intent);
            } else {
                //if there is an image
                //displaying a big notification
                mNotificationManager.showBigNotification(title, message, imageUrl, intent);
            }
        } catch (JSONException e) {
            //  Log.e(TAG, "Json Exception: " + e.getMessage());
        } catch (Exception e) {
            //  Log.e(TAG, "Exception: " + e.getMessage());
        }
    }

    // This function will create an intent. This intent must take as parameter the "unique_name" that you registered your activity with
    public static void updateMyActivity(Context context, String message) {

        Intent intent = new Intent("unique_name");

        //put whatever data you want to send, if any
        intent.putExtra("message", message);
        //send broadcast
        context.sendBroadcast(intent);

//       Lead Accepted By Other
    }

}